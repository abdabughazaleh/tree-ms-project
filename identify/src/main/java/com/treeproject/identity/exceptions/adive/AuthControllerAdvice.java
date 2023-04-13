package com.treeproject.identity.exceptions.adive;

import com.treeproject.identity.exceptions.AuthException;
import com.treeproject.identity.exceptions.LoginException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AuthControllerAdvice {

    @ExceptionHandler(AuthException.class)
    public ResponseEntity<?> handlerException(AuthException e) {
        return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
    }

}
