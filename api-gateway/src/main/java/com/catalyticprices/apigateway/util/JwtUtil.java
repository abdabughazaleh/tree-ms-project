package com.catalyticprices.apigateway.util;

import com.catalyticprices.apigateway.exception.CustomException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;


@Component
@Slf4j
public class JwtUtil {
    private JwtUtil(){}
    private static String jwtSecret = "sec0000336655421322";


    public static void jwtValidator(ServerHttpRequest request) {
        try {
            String tokenHeader = request.getHeaders().getFirst("Authorization");
            if (tokenHeader != null && tokenHeader.startsWith("Bearer ")) {
                String getTokenOnly = tokenHeader.substring(7);
                String roleFromToken = getRoleFromToken(getTokenOnly);
                String role = getRoleFromToken(getTokenOnly);
                validateRole(roleFromToken, role);
            } else {
                throw new CustomException("TOKEN_NO_VALID", HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            throw new CustomException("TOKEN_NO_VALID", HttpStatus.UNAUTHORIZED);
        }
    }

    public static String getRoleFromToken(String token) {
        Claims claims = getClaims(token);
        return (String) claims.get("role");
    }

    public static Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
    }

    public static void validateRole(String userRole, String methodRole) {
        userRole = userRole.toUpperCase();
        if (!userRole.equals("*") && !userRole.equals("ADMIN") && !userRole.equalsIgnoreCase(methodRole.toUpperCase())) {
            throw new CustomException("USER_NOT_HAVE_PRIVILEGE", HttpStatus.UNAUTHORIZED);
        }
    }
}
