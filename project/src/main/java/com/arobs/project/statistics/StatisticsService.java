package com.arobs.project.statistics;

import com.arobs.project.dtos.LateReturnEmployeeDTO;
import com.arobs.project.dtos.TopBookDTO;
import com.arobs.project.dtos.TopEmployeeDTO;

import java.sql.Timestamp;
import java.util.List;

public interface StatisticsService {
    List<TopBookDTO> topXBooksRented(int x, Timestamp beginningDate, Timestamp endDate);

    List<TopEmployeeDTO> topXEmployees(int x, Timestamp beginningDate, Timestamp endDate);

    List<LateReturnEmployeeDTO> getLateReturnEmployees();
}
