package com.arobs.project.bookRent;

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
import java.util.Date;
import java.util.List;

@Component("bookRentScheduledService")
@EnableTransactionManagement
@EnableAsync
public class BookRentScheduledService {
    private final Logger log = LoggerFactory.getLogger(BookRentScheduledService.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    private final BookRentHibernateRepository bookRentRepository;

    @Autowired
    public BookRentScheduledService(BookRentHibernateRepository bookRentRepository) {
        this.bookRentRepository = bookRentRepository;
    }

    @Async
    @Scheduled(fixedRate = 10000)//10 seconds
    @Transactional
    public void markRentalsLate() {
        log.info("Late rentals the time is now {}", dateFormat.format(new Date()));
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());
        List<BookRent> lateRentals = bookRentRepository.markRentalsLate(currentTime);
        lateRentals.forEach(bookRent -> bookRent.setBookrentStatus("late"));
    }
}
