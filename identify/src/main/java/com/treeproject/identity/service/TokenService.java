package com.treeproject.identity.service;

import authenticatorv.headers.CustomException;
import com.treeproject.identity.config.EnvironmentAccess;
import com.treeproject.identity.exceptions.AuthException;
import com.treeproject.identity.mock.UserPermissionsMock;
import com.treeproject.identity.model.dto.TokenDto;
import com.treeproject.identity.model.dto.UserDto;
import com.treeproject.identity.model.entity.Token;
import com.treeproject.identity.model.mapper.TokenMapper;
import com.treeproject.identity.repository.TokenRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
public class TokenService {

    @Autowired
    private TokenRepository tokenRepository;
    @Autowired
    private TokenMapper tokenMapper;

    private static final long serialVersionUID = 7008375124389347049L;
    public static final long TOKEN_VALIDITY = 50000;
    private static final String jwtSecret = EnvironmentAccess.jwtSecret;

    public void isValid(String token) {
        if (token != null && token.contains("Bearer")) {
            token = token.substring(7);
        }
        Optional<Token> tokenEnt = this.tokenRepository.findByTokenAndIsActive(token, true);
        if (tokenEnt.isPresent()) {
            LocalDateTime expiredAt = tokenEnt.get().getExpiredAt();
            long tokenValidSinceMins = ChronoUnit.MINUTES.between(expiredAt, LocalDateTime.now());
            // todo change the 5mins to dynamic change from configuration file.
            if (tokenValidSinceMins > 5) {
                throw new AuthException("TOKEN_EXPIRED");
            }
        } else {
            throw new AuthException("TOKEN_NOT_VALID");
        }
    }

    public void expiredAllActiveUserToken(int userId) {
        this.tokenRepository.expiredAllActiveUserToken(userId);
    }

    public String generateJwtTokenWithClimes(UserDto dto) {
        try {
            Map<String, Object> claims = new HashMap<>();
            claims.put("role", dto.getRole());
            claims.put("userId", dto.getUserId());
            //claims.put("permissions", UserPermissionsMock.get(dto.getRole()));
            return Jwts.builder()
                    .setClaims(claims)
                    .setSubject(dto.getUsername())
                    .setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(new Date(System.currentTimeMillis() + TOKEN_VALIDITY))
                    .signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
        } catch (Exception e) {
            throw new CustomException("Something Wrong Please Contact With Support - A10006");
        }
    }

    public void newAccess(String token, UserDto dto) {
        // todo change the 5mins to dynamic change from configuration file.
        LocalDateTime expiredAt = LocalDateTime.now().plusMinutes(5);
        TokenDto tokenDto = TokenDto.builder()
                .isActive(true)
                .token(token)
                .userId(dto.getUserId())
                .expiredAt(expiredAt)
                .build();
        this.expiredAllActiveUserToken(dto.getUserId());
        Token newToken = this.tokenMapper.toEntity(tokenDto);
        this.tokenRepository.save(newToken);
    }

    public void destroyToken(String token) {
        this.tokenRepository.destroyToken(token);
    }

    public boolean checkPermissions(String role, String permission) {
        log.info("checkPermissions : start checking permission {} for role {}  ", permission, role);
        if (role.equals("ADMIN")) {
            log.info("checkPermissions : start checking permission {} for role {} =>> admin role ", permission, role);
            return true;
        } else if (role.equals("USER")) {
            boolean checker = UserPermissionsMock.get("USER")
                    .get(permission)
                    .equals(true);
            log.info("checkPermissions : start checking permission {} for role {} is {} ", permission, role, checker);
            return checker;
        }
        return false;
    }

    public Integer getUserIdFromToken() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .currentRequestAttributes()).getRequest();
        String authorization = request.getHeader("Authorization");
        String token = authorization.substring(7);
        return this.getUserId(token);
    }

    public Integer getUserId(String token) {
        final Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
        return (Integer) claims.get("userId");
    }
}
