package com.arobs.project.dtos;

public class TopBookDTO {
    private String bookTitle;
    private long numberOfRents;

    public TopBookDTO() {
    }

    public TopBookDTO(String bookTitle, long numberOfRents) {
        this.bookTitle = bookTitle;
        this.numberOfRents = numberOfRents;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public long getNumberOfRents() {
        return numberOfRents;
    }

    public void setNumberOfRents(long numberOfRents) {
        this.numberOfRents = numberOfRents;
    }

    @Override
    public String toString() {
        return "BookRentedDTO{" +
                "bookTitle='" + bookTitle + '\'' +
                ", numberOfRents=" + numberOfRents +
                '}';
    }
}
