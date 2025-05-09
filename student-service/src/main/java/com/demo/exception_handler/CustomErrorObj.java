package com.demo.exception_handler;

import java.time.Instant;
import java.util.List;

public class CustomErrorObj{

    private String error;
    private String message;
    // private String path;
    private List<CustomErrorPath> paths;
    private int status;
    private Instant timestamp;

    
    public CustomErrorObj() {
        super();
        this.timestamp = Instant.now();
    }

    public CustomErrorObj(String error, String message, List<CustomErrorPath> paths, int status) {
        this();
        this.error = error;
        this.message = message;
        this.status = status;
        this.paths = paths;
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
    public List<CustomErrorPath> getPaths() {
        return paths;
    }
    public void setPaths(List<CustomErrorPath> path) {
        this.paths = paths;
    }

    @Override
    public String toString() {
        return "CustomErrorObj [error=" + error + ", message=" + message + ", paths=" + paths + ", status=" + status + ", timestamp=" + timestamp + "]";
    }

}