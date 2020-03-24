package com.arobs.project.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;

public class StatisticsInputDTO {
    int x;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private String beginningDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private String endDate;

    public StatisticsInputDTO() {
    }

    public StatisticsInputDTO(int x, String beginningDate, String endDate) {
        this.x = x;
        this.beginningDate = beginningDate;
        this.endDate = endDate;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public String getBeginningDate() {
        return beginningDate;
    }

    public void setBeginningDate(String beginningDate) {
        this.beginningDate = beginningDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "StatisticsInputDTO{" +
                "x=" + x +
                ", beginningDate='" + beginningDate + '\'' +
                ", endDate='" + endDate + '\'' +
                '}';
    }
}
