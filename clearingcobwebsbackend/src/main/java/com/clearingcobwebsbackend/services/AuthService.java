package com.clearingcobwebsbackend.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.clearingcobwebsbackend.configurations.ForbiddenException;
import com.clearingcobwebsbackend.entities.UserEntity;
import com.clearingcobwebsbackend.repositories.UserRepository;
import com.clearingcobwebsbackend.requestobjects.LoginRequestObj;
import com.clearingcobwebsbackend.security.TextEncoder;

import lombok.RequiredArgsConstructor;

@Service
@Component
@RequiredArgsConstructor
public class AuthService {
  private final UserRepository userRepository;

  public ResponseEntity<?> loginUser(LoginRequestObj loginRequestObj) throws Exception {
    UserEntity user = userRepository.findByEmail(loginRequestObj.getEmail()).get();
    if (user == null || !TextEncoder.match(loginRequestObj.getPassword(), user.getPassword())) {
      throw new ForbiddenException("Password incorrect");
    }
    return ResponseEntity.ok().body("Okay");
  }
}
