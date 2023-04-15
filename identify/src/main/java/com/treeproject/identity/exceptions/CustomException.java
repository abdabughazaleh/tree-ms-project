package com.treeproject.identity.exceptions;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
public class CustomException extends RuntimeException {
    private String message;
    private HttpStatus httpStatus;

    public CustomException(String message) {
        super(message);
    }

    public CustomException() {

    }
}
