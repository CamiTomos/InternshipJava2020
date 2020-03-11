package com.arobs.project.dtos;

import java.util.Objects;

public class EmployeeDTO {
    private int id;
    private String employeeName;
    private String employeeRole;
    private String employeePassword;
    private String employeeEmail;

    public EmployeeDTO() {
    }

    public EmployeeDTO(int id, String employeeName, String employeeRole, String employeePassword, String employeeEmail) {
        this.id = id;
        this.employeeName = employeeName;
        this.employeeRole = employeeRole;
        this.employeePassword = employeePassword;
        this.employeeEmail = employeeEmail;
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

    @Override
    public String toString() {
        return "EmployeeDTO{" +
                "id=" + id +
                ", employeeName='" + employeeName + '\'' +
                ", employeeRole='" + employeeRole + '\'' +
                ", employeePassword='" + employeePassword + '\'' +
                ", employeeEmail='" + employeeEmail + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeDTO that = (EmployeeDTO) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
