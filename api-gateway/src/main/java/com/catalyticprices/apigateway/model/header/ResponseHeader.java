package com.catalyticprices.apigateway.model.header;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
public class ResponseHeader implements Serializable {
    private HttpStatus httpStatus;
    private Integer HttpStatusCode;
    private String HeaderMessage;
    private String UserMessage;

    ResponseHeader(){

    }
}
