package com.catalyticprices.apigateway.exception.advice;

import feign.FeignException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;

@RestControllerAdvice
public class FeignExceptionHandler {

    @ExceptionHandler(FeignException.class)
    public FeignExceptionResponse handleFeignStatusException(FeignException e, HttpServletResponse response) {
        response.setStatus(e.status());
        return FeignExceptionResponse
                .builder()
                .message(e.getMessage())
                .build();
    }

}