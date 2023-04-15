package com.catalyticprices.apigateway.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
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
