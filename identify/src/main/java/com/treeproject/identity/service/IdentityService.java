package com.treeproject.identity.service;

import com.treeproject.identity.model.dto.*;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface IdentityService {
    Optional<UserDto> findByUsername(String username);
    LoginRespDto login(LoginReqDto dto);
    void logout(LogoutDto dto);
    void isValidate(TokenValidateDto dto);
}
