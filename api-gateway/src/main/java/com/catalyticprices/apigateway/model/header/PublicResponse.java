package com.catalyticprices.apigateway.model.header;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PublicResponse implements Serializable {
    private ResponseHeader header;
    private Object body;
}
