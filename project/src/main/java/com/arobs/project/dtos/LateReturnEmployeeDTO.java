package com.arobs.project.dtos;

import javax.validation.constraints.NotNull;

public class LateReturnEmployeeDTO {
    @NotNull
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
