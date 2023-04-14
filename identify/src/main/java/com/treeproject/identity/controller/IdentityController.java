package com.treeproject.identity.controller;

import com.treeproject.identity.model.dto.*;
import com.treeproject.identity.service.IdentityService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class IdentityController {
    private final IdentityService identityService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<LoginRespDto> login(@RequestBody LoginReqDto dto) {
        LoginRespDto loginDetails = this.identityService.login(dto);
        return ResponseEntity.ok(loginDetails);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public ResponseEntity logout(@RequestBody LogoutDto dto) {
        this.identityService.logout(dto);
        return ResponseEntity.status(HttpStatus.OK)
                .build();
    }

    @RequestMapping(value = "/validate", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> isValidate(@RequestBody TokenValidateDto dto) {
        this.identityService.isValidate(dto);
        return ResponseEntity.status(HttpStatus.OK)
                .build();
    }

    @RequestMapping(value = "/allowed", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> isAllowed(@RequestBody IsAllowedPermissionDto dto,
                                       @RequestHeader("Authorization") String token) {
        this.identityService.isAllowed(dto , token);
        return ResponseEntity.status(HttpStatus.OK)
                .build();
    }
}
