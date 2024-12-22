package com.clearingcobwebsbackend.services;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.clearingcobwebsbackend.configurations.ForbiddenException;
import com.clearingcobwebsbackend.configurations.NotFoundException;
import com.clearingcobwebsbackend.entities.PasswordResetToken;
import com.clearingcobwebsbackend.entities.UserEntity;
import com.clearingcobwebsbackend.repositories.ResetTokenRepository;
import com.clearingcobwebsbackend.repositories.UserRepository;
import com.clearingcobwebsbackend.requestobjects.PasswordResetRequestObj;
import com.clearingcobwebsbackend.security.TextEncoder;

import lombok.RequiredArgsConstructor;

@Service
@Component
@RequiredArgsConstructor

public class EmailService {

  private final JavaMailSender javaMailSender;
  private final UserRepository userRepository;
  private final ResetTokenRepository resetTokenRepository;

  @Value("${spring.mail.username}")
  private String emailSender;

  public ResponseEntity<?> resetEmail(PasswordResetRequestObj passwordResetRequestObj) throws Exception {
    Optional<UserEntity> maybeUser = userRepository.findByEmail(passwordResetRequestObj.getEmail());
    if (maybeUser.isPresent()) {
      UserEntity user = maybeUser.get();
      if (TextEncoder.match(passwordResetRequestObj.getSecurityAnswer(), user.getSecurityAnswer())) {
        this.sendEmail(user.getEmail(), this.generateResetToken(user.getId()),
            "Spring Boot TEST RESET");
      } else {
        throw new ForbiddenException("Answer to the security question was incorrect.");
      }
    } else {
      throw new NotFoundException("User not found");
    }
    return ResponseEntity.ok().body("Email Sent.");
  }

  private void sendEmail(String recipient, String body, String subject) {
    SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
    simpleMailMessage.setFrom(emailSender);
    simpleMailMessage.setTo(recipient);
    simpleMailMessage.setText(body);
    simpleMailMessage.setSubject(subject);

    javaMailSender.send(simpleMailMessage);
  }

  private String generateResetToken(Long userID) {
    UserEntity userEntity = userRepository.findById(userID).get();
    PasswordResetToken passwordResetToken = new PasswordResetToken();
    passwordResetToken.setToken(UUID.randomUUID().toString());
    passwordResetToken.setExpirationDate(Instant.now().plusSeconds(1800));
    passwordResetToken.setUserEntity(userEntity);
    passwordResetToken.setIsValid(true);

    resetTokenRepository.saveAndFlush(passwordResetToken);
    return passwordResetToken.getToken();
  }

}
