package com.arobs.project.bookRent;

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

    @ManyToOne
    @JoinColumn(name = "employeeID")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "copyID")
    private Copy copy;

    public BookRent() {
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
                ", employee=" + employee +
                ", copy=" + copy +
                '}';
    }
}
