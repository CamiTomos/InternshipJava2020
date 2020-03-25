package com.arobs.project.rent.rentRequest;

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

@Component("rentRequestScheduledService")
@EnableAsync
public class RentRequestScheduledService {
    private final Logger log = LoggerFactory.getLogger("FILE");
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    private final RentService rentService;

    @Autowired
    public RentRequestScheduledService(RentService rentService) {
        this.rentService = rentService;
    }

    @Async
    @Scheduled(fixedRate = 60000 * 60)//1 hour
    public void checkEmailConfirmation() {
        log.info("Email confirmation the time is now {}", dateFormat.format(new Date()));
        rentService.checkEmailConfirmation();
    }
}
