package com.clearingcobwebsbackend.services;

import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.clearingcobwebsbackend.configurations.ForbiddenException;
import com.clearingcobwebsbackend.configurations.NotFoundException;
import com.clearingcobwebsbackend.entities.UserEntity;
import com.clearingcobwebsbackend.enums.SecurityQuestion;
import com.clearingcobwebsbackend.repositories.UserRepository;
import com.clearingcobwebsbackend.requestobjects.UserRequestObj;
import com.clearingcobwebsbackend.security.TextEncoder;

import lombok.RequiredArgsConstructor;

@Service
@Component
@RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepository;
  // private final ProjectMapper projectMapper;

  public ResponseEntity<?> createUser(UserRequestObj userRequestObj) throws Exception {
    boolean userAlreadyExists = userRepository.findByEmail(userRequestObj.getEmail()).isPresent();
    String newUserSecQuestion = this.getSecurityQuestionNewUser(userRequestObj.getSecurityQuestion());

    if (newUserSecQuestion.equals("Not approved")) {
      throw new ForbiddenException("This security question is not in the approved list");
    }

    if (!userAlreadyExists) {
      UserEntity newUser = UserEntity.builder()
          .firstName(userRequestObj.getFirstName())
          .lastName(userRequestObj.getLastName())
          .email(userRequestObj.getEmail())
          .password(TextEncoder.encode(userRequestObj.getPassword()))
          .securityQuestion(newUserSecQuestion)
          .securityAnswer(TextEncoder.encode(userRequestObj.getSecurityAnswer()))
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

  // Helper Methods

  private String getSecurityQuestionNewUser(String securityQuestion) {
    for (SecurityQuestion sq : SecurityQuestion.values()) {
      String fullQuestion = securityQuestion;
      if (sq.getQuestion().equals(fullQuestion)) {
        return sq.getQuestion().toString();
      }
    }
    return "Not approved";
  }
}
