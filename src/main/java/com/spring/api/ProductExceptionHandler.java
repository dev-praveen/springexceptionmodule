package com.spring.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@ControllerAdvice
public class ProductExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Set<ErrorResponse>> handleException(ProductNotFoundException exception){

        Set<ErrorResponse> error = new HashSet<>();

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorCode(100);
        errorResponse.setErrorType("Application specific");
        errorResponse.setErrorDescription("product not found description");

        error.add(errorResponse);

        log.error("Product not found exception occurred "+exception);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Set<ErrorResponse>> handleException(Exception exception) {

        Set<ErrorResponse> error = new HashSet<>();

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorCode(200);
        errorResponse.setErrorType("Application specific");
        errorResponse.setErrorDescription("Unknown exception");

        error.add(errorResponse);

        log.error("Some unknown exception occurred "+exception);
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
