package com.arobs.project.rent.bookRent;

import com.arobs.project.rent.RentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component("bookRentScheduledService")
@EnableAsync
public class BookRentScheduledService {
    private final Logger log = LoggerFactory.getLogger("FILE");
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    private final RentService rentService;

    @Autowired
    public BookRentScheduledService(RentService rentService) {
        this.rentService = rentService;
    }

    @Async
//    @Scheduled(fixedRate = 60000 * 60 * 24)// 1 day
    @Scheduled(fixedRate = 30000)// 1 day
    public void markRentalsLate() {
        log.info("Late rentals the time is now {}", dateFormat.format(new Date()));
        rentService.markRentalsLate();
    }
}
