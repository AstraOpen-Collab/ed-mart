package com.edmart.product.exception;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
@ResponseStatus
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleCategoryNotFoundException(ProductNotFoundException ex) {
        ErrorResponse ErrorResponse = new ErrorResponse();
        ErrorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        ErrorResponse.setMessage(ex.getMessage());
        ErrorResponse.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(ErrorResponse, HttpStatus.NOT_FOUND);
    }

    // Handle validation errors
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status,
                                                                  WebRequest request) {
        ErrorResponse ErrorResponse = new ErrorResponse();
        ErrorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        ErrorResponse.setMessage("Validation error");
        ErrorResponse.setTimestamp(LocalDateTime.now());
        return new ResponseEntity<>(ErrorResponse, HttpStatus.BAD_REQUEST);
    }

}