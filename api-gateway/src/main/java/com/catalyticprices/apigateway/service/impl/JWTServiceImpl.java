package com.catalyticprices.apigateway.service.impl;

import com.catalyticprices.apigateway.model.dto.TokenValidateDto;
import com.catalyticprices.apigateway.proxy.IdentityProxy;
import com.catalyticprices.apigateway.service.JWTService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class JWTServiceImpl implements JWTService {

    private final IdentityProxy identityProxy;

    @Override
    public void validate(String token) {
        TokenValidateDto tokenDto = TokenValidateDto.builder()
                .token(token)
                .build();
        this.identityProxy.isValidate(tokenDto, token);
    }
}
