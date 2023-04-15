package com.treeproject.identity.controller;

import com.treeproject.identity.model.dto.*;
import com.treeproject.identity.service.IdentityService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class IdentityController {
    private final IdentityService identityService;

    @PostMapping(value = "/login")
    public ResponseEntity<LoginRespDto> login(@RequestBody LoginReqDto dto) {
        LoginRespDto loginDetails = this.identityService.login(dto);
        return ResponseEntity.ok(loginDetails);
    }

    @PostMapping(value = "/logout")
    public ResponseEntity<Void> logout(@RequestBody LogoutDto dto) {
        this.identityService.logout(dto);
        return ResponseEntity.status(HttpStatus.OK)
                .build();
    }

    @PostMapping(value = "/validate", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Void> isValidate(@RequestBody TokenValidateDto dto) {
        this.identityService.isValidate(dto);
        return ResponseEntity.status(HttpStatus.OK)
                .build();
    }

    @PostMapping(value = "/allowed", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Void> isAllowed(@RequestBody IsAllowedPermissionDto dto,
                                       @RequestHeader("Authorization") String token) {
        this.identityService.isAllowed(dto, token);
        return ResponseEntity.status(HttpStatus.OK)
                .build();
    }
}
