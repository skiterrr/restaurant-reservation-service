package org.example.skp2reservationservice.security.service.implementation;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.example.skp2reservationservice.security.service.TokenService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;

@Service
public class TokenServiceImplementation implements TokenService {
    @Value("${oauth.jwt.secret}")
    private String jwtSecret;
    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(jwtSecret.getBytes());
    }
    @Override
    public String generate(Claims claims) {
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, getSigningKey())
                .compact();
    }

    @Override
    public Claims parseToken(String jwt) {
        Claims claims;
        try{
           claims = Jwts.parserBuilder()
                   .setSigningKey(getSigningKey())
                   .build()
                   .parseClaimsJws(jwt)
                   .getBody();
        }catch (Exception e) {
            System.err.println("Token parsing error: " + e.getMessage());
            return null;
        }
        return claims;
    }
}
