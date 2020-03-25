package com.arobs.project.statistics;

import com.arobs.project.dtos.StatisticsInputDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Validated
@Controller("statisticsController")
@RequestMapping("/library-app")
public class StatisticsController {
    private final StatisticsService statisticsService;
    private static SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private final Logger log = LoggerFactory.getLogger("FILE");

    @Autowired
    public StatisticsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @PostMapping(value = "/statistics/topBooksRented")
    public ResponseEntity<?> handleGetTopBooksRented(@Valid @RequestBody StatisticsInputDTO inputDTO) {
        try {
            return new ResponseEntity<>(statisticsService.topXBooksRented(
                    inputDTO.getX(),
                    new Timestamp(myFormat.parse(inputDTO.getBeginningDate()).getTime()),
                    new Timestamp(myFormat.parse(inputDTO.getEndDate()).getTime())
            ), HttpStatus.OK);
        } catch (ParseException e) {
            return new ResponseEntity<>("Date does not respect the format!", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error("Server exception!");
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/statistics/topEmployees")
    public ResponseEntity<?> handleGetTopEmployees(@Valid @RequestBody StatisticsInputDTO inputDTO) {
        try {
            return new ResponseEntity<>(statisticsService.topXEmployees(
                    inputDTO.getX(),
                    new Timestamp(myFormat.parse(inputDTO.getBeginningDate()).getTime()),
                    new Timestamp(myFormat.parse(inputDTO.getEndDate()).getTime())
            ), HttpStatus.OK);
        } catch (ParseException e) {
            return new ResponseEntity<>("Date does not respect the format!", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error("Server exception!");
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/statistics/lateReturns")
    public ResponseEntity<?> handleGetLateReturns() {
        return new ResponseEntity<>(statisticsService.getLateReturnEmployees(), HttpStatus.OK);
    }
}
