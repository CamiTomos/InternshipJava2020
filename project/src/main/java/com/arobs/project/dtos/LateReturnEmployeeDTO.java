package com.arobs.project.dtos;

public class LateReturnEmployeeDTO {
    private String employeeName;

    public LateReturnEmployeeDTO() {
    }

    public LateReturnEmployeeDTO(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    @Override
    public String toString() {
        return "LateReturnEmployeeDTO{" +
                "employeeName='" + employeeName + '\'' +
                '}';
    }
}
