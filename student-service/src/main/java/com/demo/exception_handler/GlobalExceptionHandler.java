package com.demo.exception_handler;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {


    //     @ExceptionHandler(ResourceNotFoundException.class)
    // public ResponseEntity<Map<String, String>> handleResourceNotFoundException(ResourceNotFoundException ex) {
    //     Map<String, String> response = new HashMap<>();
    //     response.put("error", ex.getMessage());
    //     return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    // }

    // @ExceptionHandler(ValidationException.class)
    // public ResponseEntity<Map<String, String>> handleValidationException(ValidationException ex) {
    //     Map<String, String> response = new HashMap<>();
    //     response.put("error", ex.getMessage());
    //     return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    // }

    // @ExceptionHandler(MethodArgumentNotValidException.class)
    // public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
    //     Map<String, String> errors = new HashMap<>();
    //     ex.getBindingResult().getFieldErrors().forEach(error ->
    //         errors.put(error.getField(), error.getDefaultMessage())
    //     );
    //     return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    // }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomErrorObj> exceptionHandler(Exception ex){
        // System.out.println(ex.getMessage());

        StackTraceElement[] traces = ex.getStackTrace();
        List<CustomErrorPath> errorPaths = new ArrayList<>();
        if(traces.length>0){
            for(StackTraceElement trace:traces){
                String className = trace.getClassName();
                String method = trace.getMethodName();
                String file = trace.getFileName();
                int linenum = trace.getLineNumber();
                
                CustomErrorPath path = new CustomErrorPath(className, method, file, linenum);
                errorPaths.add(path);
                break;
            }
        }

        return ResponseEntity.status(HttpStatusCode.valueOf(500)).body(new CustomErrorObj(ex.getClass().toString(), ex.getMessage(), errorPaths, 500));
    }

}
