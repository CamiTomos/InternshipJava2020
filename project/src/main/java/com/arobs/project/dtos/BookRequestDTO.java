package com.arobs.project.dtos;

import javax.validation.constraints.NotNull;

public class BookRequestDTO {
    private int id;
    @NotNull
    private String bookrequestTitle;
    @NotNull
    private String bookrequestAuthor;
    @NotNull
    private String bookrequestPublishingCompany;
    @NotNull
    private String bookrequestLink;
    @NotNull
    private int bookrequestCopiesNeeded;
    @NotNull
    private double bookrequestTotalCost;
    @NotNull
    private String bookrequestStatus;
    @NotNull
    private int employeeId;

    public BookRequestDTO() {
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

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }
}
