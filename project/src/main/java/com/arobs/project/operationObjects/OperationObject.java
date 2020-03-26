package com.arobs.project.operationObjects;

public class OperationObject {
    private String message;

    public OperationObject(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "OperationObject{" +
                "message='" + message + '\'' +
                '}';
    }
}
