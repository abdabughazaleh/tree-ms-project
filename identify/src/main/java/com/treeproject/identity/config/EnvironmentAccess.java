package com.treeproject.identity.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Data
@Component
public class EnvironmentAccess implements Serializable {
    @Value("${jwt.secret}")
    private static String jwtSecret;

    @Value("${jwt.secret}")
    public void setJwtSecret(String key) {
        jwtSecret = key;
    }
    public static String getJwtSecret() {
        return EnvironmentAccess.jwtSecret;
    }

}
