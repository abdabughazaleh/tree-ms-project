package com.treeproject.identity.controller;

import com.treeproject.identity.model.dto.LoginReqDto;
import com.treeproject.identity.model.dto.LoginRespDto;
import com.treeproject.identity.model.dto.LogoutDto;
import com.treeproject.identity.model.dto.TokenValidateDto;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("local")
class IdentityControllerTest {

    @Autowired
    private IdentityController identityController;
    // private final TestRestTemplate restTemplate;

    @Test
    void a_login() {
        LoginReqDto loginReqDto = LoginReqDto.builder().build();
        loginReqDto.setCorrelationId(UUID.randomUUID().toString());
        loginReqDto.setPassword("user");
        loginReqDto.setUsername("user");
        //ResponseEntity<LoginRespDto> forObject = this.restTemplate
        //        .postForEntity("http://localhost:8787/identity/user/login", loginReqDto, LoginRespDto.class);
        ResponseEntity<LoginRespDto> login = this.identityController.login(loginReqDto);
        assertEquals(200, login.getStatusCode().value());
    }

    @Test
    void b_validate() {
        LoginReqDto loginReqDto = LoginReqDto.builder().build();
        loginReqDto.setCorrelationId(UUID.randomUUID().toString());
        loginReqDto.setPassword("user");
        loginReqDto.setUsername("user");
        ResponseEntity<LoginRespDto> login = this.identityController.login(loginReqDto);
        TokenValidateDto build = TokenValidateDto.builder()
                .token(login.getBody().getToken())
                .build();
        ResponseEntity<?> validate = this.identityController.isValidate(build);
        assertEquals(200, validate.getStatusCode().value());
    }

    @Test
    void c_logout() {
        LoginReqDto loginReqDto = LoginReqDto.builder().build();
        loginReqDto.setCorrelationId(UUID.randomUUID().toString());
        loginReqDto.setPassword("user");
        loginReqDto.setUsername("user");
        ResponseEntity<LoginRespDto> login = this.identityController.login(loginReqDto);
        LogoutDto build = LogoutDto.builder()
                .token(login.getBody().getToken())
                .build();
        ResponseEntity<?> validate = this.identityController.logout(build);
        assertEquals(200, validate.getStatusCode().value());
    }
}