package com.sammy.payoneer.data.error;

public class ErrorHolder {
    private String message;

    public ErrorHolder(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}