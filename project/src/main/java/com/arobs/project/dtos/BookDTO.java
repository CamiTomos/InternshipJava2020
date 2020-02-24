package com.arobs.project.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;

public class BookDTO {
    private int id;
    private String bookTitle;
    private String bookAuthor;
    private String bookDescription;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private String bookAddedDate;

    public BookDTO(int id, String bookTitle, String bookAuthor, String bookDescription, String bookAddedDate) {
        this.id = id;
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.bookDescription = bookDescription;
        this.bookAddedDate = bookAddedDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getBookDescription() {
        return bookDescription;
    }

    public void setBookDescription(String bookDescription) {
        this.bookDescription = bookDescription;
    }

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    public String getBookAddedDate() {
        return bookAddedDate;
    }

    public void setBookAddedDate(String bookAddedDate) {
        this.bookAddedDate = bookAddedDate;
    }

    @Override
    public String toString() {
        return "BookDTO{" +
                "id=" + id +
                "bookTitle='" + bookTitle + '\'' +
                ", bookAuthor='" + bookAuthor + '\'' +
                ", bookDescription='" + bookDescription + '\'' +
                ", bookAddedDate=" + bookAddedDate +
                '}';
    }
}
