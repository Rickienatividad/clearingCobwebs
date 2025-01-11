package com.clearingcobwebsbackend.services;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.clearingcobwebsbackend.configurations.ForbiddenException;
import com.clearingcobwebsbackend.entities.UserEntity;
import com.clearingcobwebsbackend.repositories.UserRepository;
import com.clearingcobwebsbackend.requestobjects.LoginRequestObj;

import lombok.RequiredArgsConstructor;

@Service
@Component
@RequiredArgsConstructor
public class AuthService {
  private final UserRepository userRepository;
  private final AuthenticationManager authManager;
  private final JWTService jwtService;

  public String loginUser(LoginRequestObj loginRequestObj) throws Exception {
    Optional<UserEntity> maybeUser = userRepository.findByEmail(loginRequestObj.getEmail());

    if (maybeUser.isEmpty()) {
      throw new ForbiddenException("Password incorrect");
    } else {
      UserEntity user = maybeUser.get();
      Authentication authentication = authManager
          .authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), loginRequestObj.getPassword()));

      if (authentication.isAuthenticated()) {
        return jwtService.generateJWT(user.getEmail());
      }
    }
    return ("Forbidden");
  }

}
