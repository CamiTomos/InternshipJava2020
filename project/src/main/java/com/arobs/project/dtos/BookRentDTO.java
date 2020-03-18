package com.arobs.project.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;

public class BookRentDTO {
    private int id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private String bookrentRentalDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private String bookrentReturnDate;
    private String bookrentStatus;
    private Double bookrentNote;
    private int employeeId;
    private int bookId;

    public BookRentDTO() {
    }

    public BookRentDTO(int id, String bookrentRentalDate, String bookrentReturnDate, String bookrentStatus, Double bookrentNote) {
        this.id = id;
        this.bookrentRentalDate = bookrentRentalDate;
        this.bookrentReturnDate = bookrentReturnDate;
        this.bookrentStatus = bookrentStatus;
        this.bookrentNote = bookrentNote;
    }

    public BookRentDTO(int id, String bookrentRentalDate, String bookrentReturnDate, String bookrentStatus, Double bookrentNote, int employeeId, int bookId) {
        this.id = id;
        this.bookrentRentalDate = bookrentRentalDate;
        this.bookrentReturnDate = bookrentReturnDate;
        this.bookrentStatus = bookrentStatus;
        this.bookrentNote = bookrentNote;
        this.employeeId = employeeId;
        this.bookId = bookId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookrentRentalDate() {
        return bookrentRentalDate;
    }

    public void setBookrentRentalDate(String bookrentRentalDate) {
        this.bookrentRentalDate = bookrentRentalDate;
    }

    public String getBookrentReturnDate() {
        return bookrentReturnDate;
    }

    public void setBookrentReturnDate(String bookrentReturnDate) {
        this.bookrentReturnDate = bookrentReturnDate;
    }

    public String getBookrentStatus() {
        return bookrentStatus;
    }

    public void setBookrentStatus(String bookrentStatus) {
        this.bookrentStatus = bookrentStatus;
    }

    public Double getBookrentNote() {
        return bookrentNote;
    }

    public void setBookrentNote(Double bookrentNote) {
        this.bookrentNote = bookrentNote;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    @Override
    public String toString() {
        return "BookRentDTO{" +
                "id=" + id +
                ", bookrentRentalDate='" + bookrentRentalDate + '\'' +
                ", bookrentReturnDate='" + bookrentReturnDate + '\'' +
                ", bookrentStatus='" + bookrentStatus + '\'' +
                ", bookrentNote=" + bookrentNote +
                ", employeeId=" + employeeId +
                ", bookId=" + bookId +
                '}';
    }

}
