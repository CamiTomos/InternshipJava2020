package com.arobs.project.dtos;

import javax.validation.constraints.NotNull;

public class RentRequestDTO {
    @NotNull
    private int employeeId;
    @NotNull
    private int bookId;

    public RentRequestDTO(int employeeId, int bookId) {
        this.employeeId = employeeId;
        this.bookId = bookId;
    }

    public RentRequestDTO() {
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    @Override
    public String toString() {
        return "RentRequestDTO{" +
                "employeeId=" + employeeId +
                ", bookId=" + bookId +
                '}';
    }
}
