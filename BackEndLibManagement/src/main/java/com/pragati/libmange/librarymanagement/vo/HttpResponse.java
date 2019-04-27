package com.pragati.libmange.librarymanagement.vo;

public class HttpResponse <T> {
    private T responseObject;
    private String message;
    private boolean status;

    public T getResponseObject() {
        return responseObject;
    }
    public void setResponseObject(T responseObject) {
        this.responseObject = responseObject;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public boolean isStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }

}
