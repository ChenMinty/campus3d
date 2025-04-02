package com.chenminty.campus3d.utils;

import com.chenminty.campus3d.config.JwtConfig;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Component
public class JwtUtils {
    private final JwtConfig jwtConfig;
    private final SecretKey secretKey;

    @Autowired
    public JwtUtils(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
        this.secretKey = Keys.hmacShaKeyFor(jwtConfig.getSecretKey().getBytes());
    }

    public String generateToken(Long userId, List<String> roles) {
        return Jwts.builder()
                .setSubject(userId.toString())
                .claim("roles", roles)
                .claim("userId", userId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtConfig.getExpireMs()))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    // 验证Token是否有效
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
            System.out.println("验证通过");
            return true;
        } catch (SignatureException ex) {
            // 签名无效
            throw new JwtException("Invalid JWT signature");
        } catch (MalformedJwtException ex) {
            // Token格式错误
            throw new JwtException("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            // Token已过期
            throw new JwtException("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            // 不支持的Token
            throw new JwtException("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            // 参数错误
            throw new JwtException("JWT claims string is empty");
        }
    }

    public Map<String, Object> parseToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
