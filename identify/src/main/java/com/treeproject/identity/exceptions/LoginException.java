package com.treeproject.identity.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class LoginException extends RuntimeException {
    private String message;
    private HttpStatus httpStatus;

    public LoginException(String message) {
        super(message);
    }

    public LoginException() {

    }
}
