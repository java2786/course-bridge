package com.demo;

import java.time.Instant;

public class CustomErrorObj{
    private String abc = "songs";
    private String error;
    private String message;
    private String path;
    private int status;
    private Instant timestamp;

    
    public CustomErrorObj() {
        super();
        this.timestamp = Instant.now();
    }

    public CustomErrorObj(String error, String message, String path, int status) {
        this();
        this.error = error;
        this.message = message;
        this.status = status;
        this.path = path;
    }
    public String getAbc() {
        return abc;
    }
    public String getError() {
        return error;
    }
    public void setError(String error) {
        this.error = error;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public Instant getInstant() {
        return timestamp;
    }
    public void setInstant(Instant timestamp) {
        this.timestamp = timestamp;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public String getPath() {
        return path;
    }
    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "CustomErrorObj [error=" + error + ", message=" + message + ", path=" + path
                + ", status=" + status + ", timestamp=" + timestamp + "]";
    }

}