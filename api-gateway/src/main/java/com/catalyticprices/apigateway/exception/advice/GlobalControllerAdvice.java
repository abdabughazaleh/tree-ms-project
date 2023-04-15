package com.catalyticprices.apigateway.exception.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handlerException(Exception e) {
        return new ResponseEntity<String>("Internal server error", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
