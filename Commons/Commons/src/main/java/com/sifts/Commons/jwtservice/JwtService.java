package com.sifts.Commons.jwtservice;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Key;
import java.util.*;
import java.util.function.Function;

@Configuration
public class JwtService {
    @Value("${jwt.expiration}")
    private long EXPIRATION_DATE;
    @Value("${jwt.refresh_token}")
    private long REFRESH_TOKEN;
    @Value("${jwt.secretkey}")
    private String SECRET_KEY;

    public String extractUsername(String token) {
        return extractClaims(token, Claims::getSubject);
    }
    public <T> T extractClaims(String token, Function<Claims,T> claimsTFunction){
        final Claims claims = extractAllClaims(token);
        return claimsTFunction.apply(claims);
    }
    public String generateToken(Authentication authentication, String roles) {
        final HashMap<String,Object> hashMap = new HashMap<>();
        hashMap.put("role",roles);
        hashMap.put("Company","");
        return buildToken(hashMap, authentication, EXPIRATION_DATE);
    }
    public String generateRefreshToken(Authentication authentication) {
        return buildToken(new HashMap<>(), authentication, REFRESH_TOKEN);
    }
    private String buildToken(Map<String, Object> extraClaims,Authentication authentication, long expiration) {
        if (extraClaims.containsKey("sub")) {
            extraClaims.put("email", extraClaims.remove("sub"));
        }
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(authentication.getName())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }


    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }
    private Date extractExpiration(String token) {
        return extractClaims(token, Claims::getExpiration);
    }
    private Claims extractAllClaims(String token) {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

}
