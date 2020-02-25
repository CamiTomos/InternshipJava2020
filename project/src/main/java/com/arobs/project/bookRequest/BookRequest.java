package com.arobs.project.bookRequest;

import com.arobs.project.employee.Employee;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "bookrequests")
public class BookRequest implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue
    private int id;

    @Column(name = "bookrequestTitle")
    private String bookrequestTitle;

    @Column(name = "bookrequestAuthor")
    private String bookrequestAuthor;

    @Column(name = "bookrequestPublishingCompany")
    private String bookrequestPublishingCompany;

    @Column(name = "bookrequestLink")
    private String bookrequestLink;

    @Column(name = "bookrequestCopiesNeeded")
    private int bookrequestCopiesNeeded;

    @Column(name = "bookrequestTotalCost")
    private double bookrequestTotalCost;

    @Column(name = "bookrequestStatus")
    private String bookrequestStatus;

    @ManyToOne
    @JoinColumn(name = "employeeID")
    private Employee employee;

    public BookRequest() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookrequestTitle() {
        return bookrequestTitle;
    }

    public void setBookrequestTitle(String bookrequestTitle) {
        this.bookrequestTitle = bookrequestTitle;
    }

    public String getBookrequestAuthor() {
        return bookrequestAuthor;
    }

    public void setBookrequestAuthor(String bookrequestAuthor) {
        this.bookrequestAuthor = bookrequestAuthor;
    }

    public String getBookrequestPublishingCompany() {
        return bookrequestPublishingCompany;
    }

    public void setBookrequestPublishingCompany(String bookrequestPublishingCompany) {
        this.bookrequestPublishingCompany = bookrequestPublishingCompany;
    }

    public String getBookrequestLink() {
        return bookrequestLink;
    }

    public void setBookrequestLink(String bookrequestLink) {
        this.bookrequestLink = bookrequestLink;
    }

    public int getBookrequestCopiesNeeded() {
        return bookrequestCopiesNeeded;
    }

    public void setBookrequestCopiesNeeded(int bookrequestCopiesNeeded) {
        this.bookrequestCopiesNeeded = bookrequestCopiesNeeded;
    }

    public double getBookrequestTotalCost() {
        return bookrequestTotalCost;
    }

    public void setBookrequestTotalCost(double bookrequestTotalCost) {
        this.bookrequestTotalCost = bookrequestTotalCost;
    }

    public String getBookrequestStatus() {
        return bookrequestStatus;
    }

    public void setBookrequestStatus(String bookrequestStatus) {
        this.bookrequestStatus = bookrequestStatus;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookRequest that = (BookRequest) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "BookRequest{" +
                "id=" + id +
                ", bookrequestTitle='" + bookrequestTitle + '\'' +
                ", bookrequestAuthor='" + bookrequestAuthor + '\'' +
                ", bookrequestPublishingCompany='" + bookrequestPublishingCompany + '\'' +
                ", bookrequestLink='" + bookrequestLink + '\'' +
                ", bookrequestCopiesNeeded=" + bookrequestCopiesNeeded +
                ", bookrequestTotalCost=" + bookrequestTotalCost +
                ", bookrequestStatus='" + bookrequestStatus + '\'' +
                ", employee=" + employee +
                '}';
    }
}
