package com.ecommerce.userservice.util;

import java.util.Date;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import com.ecommerce.userservice.repository.UserRepository;
import com.ecommerce.userservice.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import io.jsonwebtoken.Claims;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String SECRET;

    @Autowired
    private UserRepository userRepository;

    public String generateToken(String email, String role) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
        return Jwts.builder()
                .setSubject(user.getId().toString())
                .claim("email", email)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // 10 hours
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
    }

    public Claims extractClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();
    }

    public String extractUserId(String token) {
        return extractClaims(token).getSubject();
    }

    public String extractEmail(String token) {
        return extractClaims(token).get("email", String.class);
    }

    public String extractRole(String token) {
        return extractClaims(token).get("role", String.class);
    }
}