package com.ymg.locadorafilmes.security.jwt;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;

import org.springframework.beans.factory.annotation.Value;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import com.ymg.locadorafilmes.model.User;

@Service
public class JwtService {

    @Value("${security.jwt.expiration}")
    private String expiration;

    @Value("${security.jwt.signatureKey}")
    private String signatureKey;

    public String tokenGenerator(User user) {
        long expString = Long.valueOf(expiration);
        LocalDateTime dateExpiration = LocalDateTime.now().plusMinutes(expString);
        Date date = Date.from(dateExpiration.atZone(ZoneId.systemDefault()).toInstant());
    
        return Jwts
                .builder()
                .setSubject(user.getEmail())
                .setExpiration(date)
                // .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, signatureKey)
                .compact();
    }

    private Claims getClaims(String token) throws ExpiredJwtException {
        return Jwts
                .parser()
                .setSigningKey(signatureKey)
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean isValidaToken(String token) {
        try {
            Claims claims = getClaims(token);
            Date expirationDate = claims.getExpiration();
            LocalDateTime localDateTime = expirationDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
            return !LocalDateTime.now().isAfter(localDateTime);
        } catch (Exception e) {
            return false;
        }
    }

    public String getLoginUser(String token) throws ExpiredJwtException {
        return (String) getClaims(token).getSubject();
    }

}
