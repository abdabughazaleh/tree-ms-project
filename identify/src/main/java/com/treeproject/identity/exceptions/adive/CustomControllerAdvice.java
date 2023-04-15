package com.treeproject.identity.exceptions.adive;


import com.treeproject.identity.exceptions.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Component
@RestControllerAdvice
public class CustomControllerAdvice {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<HttpStatus> handlerException(CustomException e) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .build();
    }
}
