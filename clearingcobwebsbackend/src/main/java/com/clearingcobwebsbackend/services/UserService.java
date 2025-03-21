package com.clearingcobwebsbackend.services;

import java.net.http.HttpResponse;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.clearingcobwebsbackend.configurations.ForbiddenException;
import com.clearingcobwebsbackend.configurations.NotFoundException;
import com.clearingcobwebsbackend.configurations.UnauthorizedException;
import com.clearingcobwebsbackend.controllers.AuthController;
import com.clearingcobwebsbackend.entities.PasswordResetToken;
import com.clearingcobwebsbackend.entities.UserEntity;
import com.clearingcobwebsbackend.enums.SecurityQuestion;
import com.clearingcobwebsbackend.repositories.ResetTokenRepository;
import com.clearingcobwebsbackend.repositories.UserRepository;
import com.clearingcobwebsbackend.requestobjects.UpdatePasswordObj;
import com.clearingcobwebsbackend.requestobjects.UserRequestObj;
import com.clearingcobwebsbackend.security.TextEncoder;

import jakarta.websocket.Decoder.Text;
import lombok.RequiredArgsConstructor;

@Service
@Component
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final ResetTokenRepository resetTokenRepository;

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

  public List<String> getSecurityQuestions() {
    List<String> securityQuestionList = new ArrayList<String>();
    for (SecurityQuestion sq : SecurityQuestion.values()) {
      securityQuestionList.add(sq.getQuestion());
    }
    return securityQuestionList;
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

  public ResponseEntity<?> updatePassword(UpdatePasswordObj updatePasswordObj) throws Exception {
    UserEntity user = userRepository.findByEmail(updatePasswordObj.getEmail())
        .orElseThrow(() -> new NotFoundException("User Not Found"));
    PasswordResetToken resetToken = resetTokenRepository.findByToken(updatePasswordObj.getResetToken())
        .orElseThrow(() -> new NotFoundException("Token Not Valid"));
    if (Instant.now().isAfter(resetToken.getExpirationDate())
        || !resetToken.getUserEntity().getId().equals(user.getId())) {
      throw new UnauthorizedException("Token Not Valid");
    }
    user.setPassword(TextEncoder.encode(updatePasswordObj.getNewPassword()));
    userRepository.saveAndFlush(user);

    resetTokenRepository.delete(resetToken);
    return ResponseEntity.status(200).body("Password Updated.");
  }
}
