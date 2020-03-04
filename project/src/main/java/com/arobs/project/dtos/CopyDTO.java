package com.arobs.project.dtos;

public class CopyDTO {
    private int id;
    private boolean copyFlag;
    private String copyStatus;
    private int bookId;

    public CopyDTO() {
    }

    public CopyDTO(int id, boolean copyFlag, String copyStatus, int bookId) {
        this.id = id;
        this.copyFlag = copyFlag;
        this.copyStatus = copyStatus;
        this.bookId = bookId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isCopyFlag() {
        return copyFlag;
    }

    public void setCopyFlag(boolean copyFlag) {
        this.copyFlag = copyFlag;
    }

    public String getCopyStatus() {
        return copyStatus;
    }

    public void setCopyStatus(String copyStatus) {
        this.copyStatus = copyStatus;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }
}
