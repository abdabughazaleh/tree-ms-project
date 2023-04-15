package com.catalyticprices.apigateway.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
@Builder
public class AuthException extends RuntimeException {
    private String message;
    private HttpStatus httpStatus;

    public AuthException(String message) {
        super(message);
    }

    public AuthException() {

    }
}
