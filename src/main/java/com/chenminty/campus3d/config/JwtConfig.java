package com.chenminty.campus3d.config;

import io.jsonwebtoken.security.Keys;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

@Component
@Data
@ConfigurationProperties(prefix = "jwt")
public class JwtConfig {
    private String secretKey;
    private long expireMs;
    private String issuer;
}
