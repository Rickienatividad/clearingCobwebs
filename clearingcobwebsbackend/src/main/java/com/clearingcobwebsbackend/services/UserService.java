package com.clearingcobwebsbackend.services;

import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.clearingcobwebsbackend.configurations.NotFoundException;
import com.clearingcobwebsbackend.entities.UserEntity;
import com.clearingcobwebsbackend.mapper.ProjectMapper;
import com.clearingcobwebsbackend.models.AppUser;
import com.clearingcobwebsbackend.repositories.UserRepository;
import com.clearingcobwebsbackend.requestobjects.UserRequestObj;

import lombok.RequiredArgsConstructor;

@Service
@Component
@RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepository;
  // private final ProjectMapper projectMapper;

  public ResponseEntity<?> createUser(UserRequestObj userRequestObj) {
    boolean userAlreadyExists = userRepository.findByEmail(userRequestObj.getEmail()).isPresent();

    if (!userAlreadyExists) {
      UserEntity newUser = UserEntity.builder()
          .firstName(userRequestObj.getFirstName())
          .lastName(userRequestObj.getLastName())
          .email(userRequestObj.getEmail())
          .password(userRequestObj.getPassword())
          .build();

      userRepository.saveAndFlush(newUser);
      return ResponseEntity.status(HttpStatus.CREATED).body("User created.");
    } else
      return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body("Email already taken.");
  }

  public List<UserEntity> findUserEntities() {
    List<UserEntity> allUsers = userRepository.findAll();
    return allUsers;
  }

  public UserEntity findUserByEmail(String email) throws Exception {
    Optional<UserEntity> maybeUser = userRepository.findByEmail(email);
    if (maybeUser.isPresent()) {
      UserEntity user = maybeUser.get();
      return user;
    } else {
      throw new NotFoundException("User not found");
    }
  }
}
