package com.catalyticprices.apigateway.model.header;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class BadHeader extends ResponseHeader implements Serializable {


    public BadHeader() {
        super(HttpStatus.OK, HttpStatus.OK.value(), "BAD_REQUEST", null);
    }

    public BadHeader(String message, HttpStatus httpStatus) {
        super(httpStatus, httpStatus.value(), message, null);
    }

    public BadHeader(String HeaderMessage) {
        super(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value(), HeaderMessage, null);
    }
    public BadHeader(String HeaderMessage , String message) {
        super(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value(), HeaderMessage, message);
    }
}
