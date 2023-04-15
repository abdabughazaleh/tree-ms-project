package com.catalyticprices.apigateway.proxy;

import com.catalyticprices.apigateway.model.dto.TokenValidateDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "IDENTITY", path = "/identity")
public interface IdentityProxy {
    @RequestMapping(value = "/user/validate", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    ResponseEntity<Void> isValidate(TokenValidateDto dto, @RequestHeader("Authorization") String token);
}
