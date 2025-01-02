package com.clearingcobwebsbackend.controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
// @CrossOrigin(origins = "http://localhost:5173")
public class AuthController {
  private final AuthService authService;

  @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> loginUser(@Valid @RequestBody LoginRequestObj loginRequestObj) throws Exception {
    return authService.loginUser(loginRequestObj);
  }
}
