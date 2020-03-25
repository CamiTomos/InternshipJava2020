package com.arobs.project.rentRequest;

import com.arobs.project.book.Book;
import com.arobs.project.bookRent.BookRentScheduledService;
import com.arobs.project.copy.CopyService;
import com.arobs.project.dtos.CopyDTO;
import com.arobs.project.enums.CopyStatus;
import com.arobs.project.enums.RentRequestStatus;
import com.arobs.project.exception.ValidationException;
import com.arobs.project.managers.RentManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component("rentRequestScheduledService")
@EnableTransactionManagement
@EnableAsync
public class RentRequestScheduledService {
    private final Logger log = LoggerFactory.getLogger("FILE");
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    private final RentRequestHibernateRepository rentRequestRepository;
    private final RentManager rentManager;
    private final RentRequestService rentRequestService;
    private final CopyService copyService;

    @Autowired
    public RentRequestScheduledService(RentRequestHibernateRepository rentRequestRepository, RentManager rentManager, RentRequestService rentRequestService, CopyService copyService) {
        this.rentRequestRepository = rentRequestRepository;
        this.rentManager = rentManager;
        this.rentRequestService = rentRequestService;
        this.copyService = copyService;
    }

    @Async
    @Scheduled(fixedRate = 60000 * 60)//1 hour
    @Transactional
    public void checkEmailConfirmation() {
        log.info("Email confirmation the time is now {}", dateFormat.format(new Date()));
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        List<RentRequest> waitingConfirmationRequests = rentRequestRepository.findWaitingConfirmationRequests();
        waitingConfirmationRequests.forEach(
                rentRequest -> {
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(rentRequest.getEmailSentDate());
                    calendar.add(Calendar.HOUR_OF_DAY, 24);
                    if (currentTime.after(calendar.getTime())) {
                        RentRequest rentRequestToBeUpdated = rentRequest;
                        rentRequestToBeUpdated.setRentrequestStatus(RentRequestStatus.DECLINED.toString().toLowerCase());
                        rentRequestRepository.updateRentRequest(rentRequestToBeUpdated);
                        Book book = rentRequestToBeUpdated.getBook();
                        List<CopyDTO> pendingCopies = copyService.findPendingCopiesForBook(book.getId());
                        CopyDTO copyToBeUpdated = pendingCopies.get(0);
                        try {
                            notifyNextEmployee(book.getId(), copyToBeUpdated);
                        } catch (ValidationException e) {
                            System.out.println("Copy could not be marked as available!");
                        }
                    }
                }
        );
    }

    private void notifyNextEmployee(int bookId, CopyDTO copyDTO) throws ValidationException {
        List<RentRequest> requestsFoundForThisBook = rentRequestService.findWaitingAvailableCopiesRequests(bookId);
        if (!requestsFoundForThisBook.isEmpty()) {
            RentRequest selectedRequest = requestsFoundForThisBook.get(0);
            copyDTO.setCopyStatus(CopyStatus.PENDING.toString().toLowerCase());
            copyService.updateCopy(copyDTO);
            selectedRequest.setRentrequestStatus(RentRequestStatus.WAITING_CONFIRMATION.toString().toLowerCase());
            selectedRequest.setEmailSentDate(new Timestamp(System.currentTimeMillis()));
            rentRequestService.sendEmail(selectedRequest);//here, the email is sent
        } else {
            copyDTO.setCopyStatus(CopyStatus.AVAILABLE.toString().toLowerCase());
            copyService.updateCopy(copyDTO);
        }
    }
}
