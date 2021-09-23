package com.sammy.payoneer.data.error;

public class ErrorHolder {
    private String message;

    public int getCode() {
        return code;
    }

    private int code;

    public ErrorHolder(String message,int code) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}