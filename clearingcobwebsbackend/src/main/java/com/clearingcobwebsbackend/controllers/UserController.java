package com.clearingcobwebsbackend.controllers;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;

import com.clearingcobwebsbackend.entities.UserEntity;
import com.clearingcobwebsbackend.models.AppUser;
import com.clearingcobwebsbackend.repositories.UserRepository;
import com.clearingcobwebsbackend.services.UserService;

import java.util.List;

import org.springframework.http.ResponseEntity;

@RequiredArgsConstructor // eliminates need to write constructor injection code for private final
                         // instances(ex lines 20,21)
@RestController
@RequestMapping("/users")
public class UserController {

  private final UserService userService;

  @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
  public void createUser(@RequestBody AppUser appUser) {
    userService.createUser(appUser);
  }

  @GetMapping("")
  public List<UserEntity> getUserIndex() {
    return userService.findUserEntities();
  }
}
