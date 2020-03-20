package com.arobs.project.dtos;

public class BookRentDTO {
    private int id;
    //    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
//    private String bookrentRentalDate;
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
//    private String bookrentReturnDate;
//    private String bookrentStatus;
//    private Double bookrentNote;
    private int employeeId;
    private int bookId;

    public BookRentDTO() {
    }

    public BookRentDTO(int id, int employeeId, int bookId) {
        this.id = id;
        this.employeeId = employeeId;
        this.bookId = bookId;
    }

    public BookRentDTO(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
                ", employeeId=" + employeeId +
                ", bookId=" + bookId +
                '}';
    }

}
