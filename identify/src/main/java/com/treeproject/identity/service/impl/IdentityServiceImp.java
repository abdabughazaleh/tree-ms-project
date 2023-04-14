package com.treeproject.identity.service.impl;

import com.treeproject.identity.exceptions.LoginException;
import com.treeproject.identity.mock.UserPermissionsMock;
import com.treeproject.identity.model.dto.*;
import com.treeproject.identity.model.entity.User;
import com.treeproject.identity.model.mapper.UserMapper;
import com.treeproject.identity.repository.UserRepository;
import com.treeproject.identity.service.IdentityService;
import com.treeproject.identity.service.TokenService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
            this.tokenService.newAccess(token , user.get());
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
        this.tokenService.destroyToken(dto.getToken());
    }

    @Override
    public void isValidate(TokenValidateDto dto) {
        this.tokenService.isValid(dto.getToken());
    }
}
