package com.catalyticprices.apigateway.exception.advice;


import com.catalyticprices.apigateway.exception.CustomException;
import com.catalyticprices.apigateway.model.header.BadHeader;
import com.catalyticprices.apigateway.model.header.PublicResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Component
@RestControllerAdvice
public class CustomControllerAdvice {

    @ExceptionHandler(CustomException.class)
    public PublicResponse handlerException(CustomException e) {
        return PublicResponse.builder()
                .header(new BadHeader(e.getMessage(), e.getMessage()))
                .body(null)
                .build();
    }
}
