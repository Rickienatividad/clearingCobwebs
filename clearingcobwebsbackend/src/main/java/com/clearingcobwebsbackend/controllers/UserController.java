package com.clearingcobwebsbackend.controllers;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;

import com.clearingcobwebsbackend.entities.UserEntity;
import com.clearingcobwebsbackend.requestobjects.PasswordResetRequestObj;
import com.clearingcobwebsbackend.requestobjects.UpdatePasswordObj;
import com.clearingcobwebsbackend.requestobjects.UserRequestObj;
import com.clearingcobwebsbackend.services.EmailService;
import com.clearingcobwebsbackend.services.UserService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.ResponseEntity;

@RequiredArgsConstructor // eliminates need to write constructor injection code for private final
                         // instances(ex lines 20,21)
@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

  private final UserService userService;
  private final EmailService emailService;

  @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> createUser(@Valid @RequestBody UserRequestObj userRequestObj) throws Exception {
    return userService.createUser(userRequestObj);
  }

  @GetMapping("")
  public List<UserEntity> getUserIndex() {
    return userService.findUserEntities();
  }

  @GetMapping("/email/{email}")
  public UserEntity findUserByEmail(@PathVariable String email) throws Exception {
    return userService.findUserByEmail(email);
  }

  @PostMapping("/reset/password")
  public ResponseEntity<?> requestPasswordChange(@RequestBody PasswordResetRequestObj passwordResetRequestObj)
      throws Exception {
    return emailService.requestPasswordChange(passwordResetRequestObj);
  }

  @GetMapping("/security-questions")
  public List<String> getSecurityQuestions() {
    return userService.getSecurityQuestions();
  }

  @PostMapping("/update-password")
  public ResponseEntity<?> updatePassword(@RequestBody UpdatePasswordObj updatePasswordObj)
      throws Exception {
    return userService.updatePassword(updatePasswordObj);
  }

}
