package com.arobs.project.rent.bookRent;

import com.arobs.project.book.Book;
import com.arobs.project.copy.Copy;
import com.arobs.project.employee.Employee;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "bookrents")
public class BookRent implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue
    private int id;

    @Column(name = "bookrentRentalDate")
    private Timestamp bookrentRentalDate;

    @Column(name = "bookrentReturnDate")
    private Timestamp bookrentReturnDate;

    @Column(name = "bookrentStatus")
    private String bookrentStatus;

    @Column(name = "bookrentNote")
    private Double bookrentNote;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employeeID")
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "copyID")
    private Copy copy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bookID")
    private Book book;

    public BookRent() {
    }

    public BookRent(int id, Timestamp bookrentRentalDate, Timestamp bookrentReturnDate, String bookrentStatus, Double bookrentNote, Employee employee, Copy copy, Book book) {
        this.id = id;
        this.bookrentRentalDate = bookrentRentalDate;
        this.bookrentReturnDate = bookrentReturnDate;
        this.bookrentStatus = bookrentStatus;
        this.bookrentNote = bookrentNote;
        this.employee = employee;
        this.copy = copy;
        this.book = book;
    }

    public BookRent(int id, Timestamp bookrentRentalDate, Timestamp bookrentReturnDate, String bookrentStatus, Double bookrentNote) {
        this.id = id;
        this.bookrentRentalDate = bookrentRentalDate;
        this.bookrentReturnDate = bookrentReturnDate;
        this.bookrentStatus = bookrentStatus;
        this.bookrentNote = bookrentNote;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getBookrentRentalDate() {
        return bookrentRentalDate;
    }

    public void setBookrentRentalDate(Timestamp bookrentRentalDate) {
        this.bookrentRentalDate = bookrentRentalDate;
    }

    public Timestamp getBookrentReturnDate() {
        return bookrentReturnDate;
    }

    public void setBookrentReturnDate(Timestamp bookrentReturnDate) {
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

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Copy getCopy() {
        return copy;
    }

    public void setCopy(Copy copy) {
        this.copy = copy;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookRent bookRent = (BookRent) o;
        return id == bookRent.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "BookRent{" +
                "id=" + id +
                ", bookrentRentalDate=" + bookrentRentalDate +
                ", bookrentReturnDate=" + bookrentReturnDate +
                ", bookrentStatus='" + bookrentStatus + '\'' +
                ", bookrentNote=" + bookrentNote +
                '}';
    }
}
