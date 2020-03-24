package com.arobs.project.statistics;

import com.arobs.project.dtos.LateReturnEmployeeDTO;
import com.arobs.project.dtos.TopBookDTO;
import com.arobs.project.dtos.TopEmployeeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

@Service("statisticsService")
@EnableTransactionManagement
public class StatisticsServiceImpl implements StatisticsService {
    private final StatisticsRepository statisticsRepository;

    @Autowired
    public StatisticsServiceImpl(StatisticsRepository statisticsRepository) {
        this.statisticsRepository = statisticsRepository;
    }

    @Override
    @Transactional
    public List<TopBookDTO> topXBooksRented(int x, Timestamp beginningDate, Timestamp endDate) {
        return statisticsRepository.topXBooksRented(x, beginningDate, endDate);
    }

    @Override
    @Transactional
    public List<TopEmployeeDTO> topXEmployees(int x, Timestamp beginningDate, Timestamp endDate) {
        return statisticsRepository.topXEmployees(x, beginningDate, endDate);
    }

    @Override
    @Transactional
    public List<LateReturnEmployeeDTO> getLateReturnEmployees() {
        return statisticsRepository.getLateReturnEmployees();
    }
}
