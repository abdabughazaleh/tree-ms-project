package com.catalyticprices.apigateway.exception.advice;

import com.catalyticprices.apigateway.exception.AuthException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AuthControllerAdvice {

    @ExceptionHandler(AuthException.class)
    public ResponseEntity<String> handlerException(AuthException e) {
        return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
    }

}
