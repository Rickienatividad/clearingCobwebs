package com.clearingcobwebsbackend.services;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTService {

  @Value("${jwt.secret}")
  private String secretKey;

  public String generateJWT(String email) {
    Map<String, Object> claims = new HashMap<>();

    return Jwts.builder()
        .claims()
        .add(claims)
        .subject(email)
        .issuedAt(new Date(System.currentTimeMillis()))
        .expiration(new Date(System.currentTimeMillis() + 3600000))
        .and()
        .signWith(getKey())
        .compact();
  }

  public String extractEmail(String jwtToken) {
    return extractClaim(jwtToken, Claims::getSubject);
  }

  public <T> T extractClaim(String jwtToken, Function<Claims, T> claimsResolver) {
    final Claims claims = extractAllClaims(jwtToken);
    return claimsResolver.apply(claims);
  }

  private Claims extractAllClaims(String jwtToken) {
    return Jwts.parser().verifyWith(getKey()).build().parseSignedClaims(jwtToken).getPayload();
  }

  public boolean validateToken(String jwtToken, UserDetails userDetails) {
    final String email = extractEmail(jwtToken);
    return email.equals(userDetails.getUsername()) && !isTokenExpired(jwtToken);
  }

  private boolean isTokenExpired(String jwtToken) {
    return extractExpiration(jwtToken).before(new Date());
  }

  private Date extractExpiration(String jwtToken) {
    return extractClaim(jwtToken, Claims::getExpiration);
  }

  private SecretKey getKey() {

    byte[] keyBytes = Decoders.BASE64.decode(secretKey);
    return Keys.hmacShaKeyFor(keyBytes);
  }

}
