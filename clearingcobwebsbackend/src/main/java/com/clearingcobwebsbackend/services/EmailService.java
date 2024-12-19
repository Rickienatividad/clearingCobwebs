package com.clearingcobwebsbackend.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@Component
@RequiredArgsConstructor

public class EmailService {

  private final JavaMailSender javaMailSender;

  @Value("${spring.mail.username}")
  private String emailSender;

  public void sendEmail(String recipient, String body, String subject) {
    SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
    simpleMailMessage.setFrom(emailSender);
    simpleMailMessage.setTo(recipient);
    simpleMailMessage.setText(body);
    simpleMailMessage.setSubject(subject);

    javaMailSender.send(simpleMailMessage);
  }

}
