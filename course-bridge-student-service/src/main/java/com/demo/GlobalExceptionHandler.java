package com.demo;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomErrorObj> exceptionHandler(Exception ex){
        System.out.println(ex.getMessage());


        return ResponseEntity.status(HttpStatusCode.valueOf(500)).body(new CustomErrorObj(ex.getClass().toString(), ex.getMessage(), null, 500));
    }

}
