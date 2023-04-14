package com.treeproject.identity.exceptions.adive;

import com.treeproject.identity.exceptions.LoginException;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class JWTControllerAdvice {

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity handlerException(ExpiredJwtException e) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .build();
    }

}
