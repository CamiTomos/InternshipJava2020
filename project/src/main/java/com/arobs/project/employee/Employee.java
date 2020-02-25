package com.arobs.project.employee;

import com.arobs.project.bookRent.BookRent;
import com.arobs.project.bookRequest.BookRequest;
import com.arobs.project.rentRequest.RentRequest;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "employees")
public class Employee implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "employeeName")
    private String employeeName;

    @Column(name = "employeeRole")
    private String employeeRole;

    @Column(name = "employeePassword")
    private String employeePassword;

    @Column(name = "employeeEmail")
    private String employeeEmail;

    @OneToMany(mappedBy = "employee")
    private Set<RentRequest> rentRequests = new HashSet<>();

    @OneToMany(mappedBy = "employee")
    private Set<BookRent> bookRents = new HashSet<>();

    @OneToMany(mappedBy = "employee")
    private Set<BookRequest> bookRequests = new HashSet<>();

    public Employee() {
    }

    public Employee(int id, String employeeName, String employeeRole, String employeePassword, String employeeEmail) {
        this.id = id;
        this.employeeName = employeeName;
        this.employeeRole = employeeRole;
        this.employeePassword = employeePassword;
        this.employeeEmail = employeeEmail;
    }

    public Employee(String employeeName, String employeeRole, String employeePassword, String employeeEmail) {
        this(1, employeeName, employeeRole, employeePassword, employeeEmail);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeRole() {
        return employeeRole;
    }

    public void setEmployeeRole(String employeeRole) {
        this.employeeRole = employeeRole;
    }

    public String getEmployeePassword() {
        return employeePassword;
    }

    public void setEmployeePassword(String employeePassword) {
        this.employeePassword = employeePassword;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

    public Set<RentRequest> getRentRequests() {
        return rentRequests;
    }

    public void setRentRequests(Set<RentRequest> rentRequests) {
        this.rentRequests = rentRequests;
    }

    public Set<BookRent> getBookRents() {
        return bookRents;
    }

    public void setBookRents(Set<BookRent> bookRents) {
        this.bookRents = bookRents;
    }

    public Set<BookRequest> getBookRequests() {
        return bookRequests;
    }

    public void setBookRequests(Set<BookRequest> bookRequests) {
        this.bookRequests = bookRequests;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id == employee.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", employeeName='" + employeeName + '\'' +
                ", employeeRole='" + employeeRole + '\'' +
                ", employeePassword='" + employeePassword + '\'' +
                ", employeeEmail='" + employeeEmail + '\'' +
                ", rentRequests=" + rentRequests +
                ", bookRents=" + bookRents +
                ", bookRequests=" + bookRequests +
                '}';
    }
}
