package com.arobs.project.rentRequest;

import com.arobs.project.book.Book;
import com.arobs.project.employee.Employee;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "rentrequests")
public class RentRequest implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue
    private int id;

    @Column(name = "rentrequestRequestDate")
    private Timestamp rentrequestRequestDate;

    @Column(name = "rentrequestStatus")
    private String rentrequestStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employeeID")
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bookID")
    private Book book;

    public RentRequest() {
    }

    public RentRequest(int id, Timestamp rentrequestRequestDate, String rentrequestStatus, Employee employee, Book book) {
        this.id = id;
        this.rentrequestRequestDate = rentrequestRequestDate;
        this.rentrequestStatus = rentrequestStatus;
        this.employee = employee;
        this.book = book;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getRentrequestRequestDate() {
        return rentrequestRequestDate;
    }

    public void setRentrequestRequestDate(Timestamp rentrequestRequestDate) {
        this.rentrequestRequestDate = rentrequestRequestDate;
    }

    public String getRentrequestStatus() {
        return rentrequestStatus;
    }

    public void setRentrequestStatus(String rentrequestStatus) {
        this.rentrequestStatus = rentrequestStatus;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
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
        RentRequest that = (RentRequest) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "RentRequest{" +
                "id=" + id +
                ", rentrequestRequestDate=" + rentrequestRequestDate +
                ", rentrequestStatus='" + rentrequestStatus + '\'' +
//                ", employee=" + employee +
//                ", book=" + book +
                '}';
    }
}
