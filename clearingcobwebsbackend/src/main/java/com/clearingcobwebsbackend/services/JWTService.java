package com.clearingcobwebsbackend.services;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
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

  private Key getKey() {

    // try {
    // KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
    // SecretKey sk = keyGen.generateKey();
    // secretKey = Base64.getEncoder().encodeToString(sk.getEncoded());
    // } catch (NoSuchAlgorithmException error) {
    // throw new RuntimeException(error);
    // }

    byte[] keyBytes = Decoders.BASE64.decode(secretKey);
    return Keys.hmacShaKeyFor(keyBytes);
  }

}
