package com.proj.blogapp.exceptions;

import com.proj.blogapp.payload.ApiResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, String> errMap = new HashMap<>();
        for (FieldError err : ex.getFieldErrors()) {
            errMap.put(err.getField(), err.getDefaultMessage());
        }
        return new ResponseEntity<>(errMap, HttpStatus.BAD_REQUEST);
    }

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
//        Map<String, String> errMap = new HashMap<>();
//        for (FieldError err : ex.getFieldErrors()) {
//            errMap.put(err.getField(), err.getDefaultMessage());
//        }
//        return new ResponseEntity<>(errMap, HttpStatus.BAD_REQUEST);
//    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {
        String message = ex.getMessage();

        ApiResponse apiResponse = new ApiResponse(message, false);

        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }
}
