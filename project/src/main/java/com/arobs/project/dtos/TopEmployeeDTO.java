package com.arobs.project.dtos;

import javax.validation.constraints.NotNull;

public class TopEmployeeDTO {
    @NotNull
    private String employeeName;
    @NotNull
    private long numberOfBooksRead;

    public TopEmployeeDTO() {
    }

    public TopEmployeeDTO(String employeeName, long numberOfBooksRead) {
        this.employeeName = employeeName;
        this.numberOfBooksRead = numberOfBooksRead;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public long getNumberOfBooksRead() {
        return numberOfBooksRead;
    }

    public void setNumberOfBooksRead(long numberOfBooksRead) {
        this.numberOfBooksRead = numberOfBooksRead;
    }

    @Override
    public String toString() {
        return "TopEmployeeDTO{" +
                "employeeName='" + employeeName + '\'' +
                ", numberOfBooksRead=" + numberOfBooksRead +
                '}';
    }
}
