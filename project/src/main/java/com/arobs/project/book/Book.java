package com.arobs.project.book;

import java.sql.Timestamp;

public class Book {
    private int id;
    private String bookTitle;
    private String bookAuthor;
    private String bookDescription;
    private Timestamp bookAddedDate;

    public Book(int id, String bookTitle, String bookAuthor, String bookDescription, Timestamp bookAddedDate) {
        this.id = id;
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.bookDescription = bookDescription;
        this.bookAddedDate = bookAddedDate;
    }

    public Book(String bookTitle, String bookAuthor, String bookDescription, Timestamp bookAddedDate) {
        this(1,bookTitle, bookAuthor, bookDescription,bookAddedDate);
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

    public Timestamp getBookAddedDate() {
        return bookAddedDate;
    }

    public void setBookAddedDate(Timestamp bookAddedDate) {
        this.bookAddedDate = bookAddedDate;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", bookTitle='" + bookTitle + '\'' +
                ", bookAuthor='" + bookAuthor + '\'' +
                ", bookDescription='" + bookDescription + '\'' +
                ", bookAddedDate=" + bookAddedDate +
                '}';
    }

//    public static void main(String[] args) {
//        Book book=new Book(1,"a","a","a", Timestamp.valueOf("2020-01-01 10:10:10"));
//        System.out.println(book.getBookAddedDate().getTime());
//        System.out.println(book.toString());
//    }
}
