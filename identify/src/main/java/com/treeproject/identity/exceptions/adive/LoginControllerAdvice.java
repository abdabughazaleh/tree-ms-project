package com.treeproject.identity.exceptions.adive;

import com.treeproject.identity.exceptions.LoginException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class LoginControllerAdvice {

    @ExceptionHandler(LoginException.class)
    public ResponseEntity handlerException(LoginException e) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .build();
    }

}
