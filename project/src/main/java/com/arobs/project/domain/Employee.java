package com.arobs.project.domain;

import java.util.Objects;

public class Employee {
    private int id;
    private String employeeName;
    private Role employeeRole;
    private String employeePassword;
    private String employeeEmail;

    public Employee(int id, String employeeName, Role employeeRole, String employeePassword, String employeeEmail) {
        this.id = id;
        this.employeeName = employeeName;
        this.employeeRole = employeeRole;
        this.employeePassword = employeePassword;
        this.employeeEmail = employeeEmail;
    }

    public Employee(String employeeName, Role employeeRole, String employeePassword, String employeeEmail) {
        this(1,employeeName,employeeRole,employeePassword,employeeEmail);
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

    public Role getEmployeeRole() {
        return employeeRole;
    }

    public void setEmployeeRole(Role employeeRole) {
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
}
