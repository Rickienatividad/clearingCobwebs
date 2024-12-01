package controllers;

import org.springframework.stereotype.Controller;

import lombok.RequiredArgsConstructor;
import models.AppUser;
import repositories.UserRepository;
import entities.UserEntity;
import services.UserService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import org.springframework.http.ResponseEntity;

@RequiredArgsConstructor // eliminates need to write constructor injection code for private final
                         // instances(ex lines 20,21)
@Controller
public class UserController {

  private final UserService userService;

  @PostMapping("/users")
  public void createUser(@RequestBody AppUser appUser) {
    userService.createUser(appUser);
  }

  @GetMapping("/users")
  public List<UserEntity> getUserIndex() {
    return userService.findUserEntities();
  }
}
