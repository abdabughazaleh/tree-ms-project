package com.treeproject.identity.service.impl;

import authenticatorv.headers.CustomException;
import com.treeproject.identity.exceptions.LoginException;
import com.treeproject.identity.mock.UserPermissionsMock;
import com.treeproject.identity.model.dto.*;
import com.treeproject.identity.model.entity.Token;
import com.treeproject.identity.model.entity.User;
import com.treeproject.identity.model.mapper.UserMapper;
import com.treeproject.identity.repository.UserRepository;
import com.treeproject.identity.service.IdentityService;
import com.treeproject.identity.service.TokenService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class IdentityServiceImp implements IdentityService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final TokenService tokenService;

    @Override
    public Optional<UserDto> findByUsername(String username) {
        Optional<User> user = this.userRepository.findByUsername(username);
        return user.map(this.userMapper::toDto);
    }

    @Override
    public LoginRespDto login(LoginReqDto dto) {
        log.info("User {} with  trying to login CorrelationId : {}", dto.getUsername(), dto.getCorrelationId());
        Optional<UserDto> user = this.findByUsername(dto.getUsername());
        user.orElseThrow(() -> new LoginException("User name or password not correct"));
        log.info("Username {} exist CorrelationId : {}", dto.getUsername(), dto.getCorrelationId());
        String password = user.get().getPassword();
        Integer userId = user.get().getUserId();
        if (dto.getPassword().equals(password)) {
            log.info("Username {} exist CorrelationId : {}", dto.getUsername(), dto.getCorrelationId());
            this.tokenService.expiredAllActiveUserToken(userId);
            log.info("Username {} successfully  expired all active old token CorrelationId : {}", dto.getUsername(), dto.getCorrelationId());
            String token = this.tokenService.generateJwtTokenWithClimes(user.get());
            this.tokenService.newAccess(token, user.get());
            return LoginRespDto.builder()
                    .token(token)
                    .permissions(UserPermissionsMock.get(user.get().getRole()))
                    .build();
        } else {
            log.info("Username or password wrong CorrelationId : {}", dto.getCorrelationId());
            throw new LoginException("User name or password not correct");
        }
    }

    @Override
    public void logout(LogoutDto dto) {
        log.info("Token {} ask for logout", dto.getToken());
        this.tokenService.destroyToken(dto.getToken());
    }

    @Override
    public void isValidate(TokenValidateDto dto) {
        log.info("Validate token {} ", dto.getToken());
        this.tokenService.isValid(dto.getToken());
    }

    @Override
    public Boolean isAllowed(IsAllowedPermissionDto dto, String token) {
        this.tokenService.isValid(token);
        Integer userId = this.tokenService.getUserIdFromToken();
        log.info("get userId {}  from  token {} ", userId, token);
        Optional<User> byId = this.userRepository.findById(userId);
        log.info("find user details {}  ", byId.get());
        boolean check = this.tokenService.checkPermissions(byId.get().getRole(), dto.getPermission());
        if (!check) {
            throw new CustomException("USER_NOT_ALLOWED", HttpStatus.FORBIDDEN);
        }
        return true;
    }
}
