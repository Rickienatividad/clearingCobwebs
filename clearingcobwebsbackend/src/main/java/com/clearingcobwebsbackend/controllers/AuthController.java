package com.clearingcobwebsbackend.controllers;

import java.security.NoSuchAlgorithmException;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.clearingcobwebsbackend.requestobjects.LoginRequestObj;
import com.clearingcobwebsbackend.services.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
  private final AuthService authService;

  @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
  public String loginUser(@Valid @RequestBody LoginRequestObj loginRequestObj) throws Exception {
    return authService.loginUser(loginRequestObj);
  }
}
