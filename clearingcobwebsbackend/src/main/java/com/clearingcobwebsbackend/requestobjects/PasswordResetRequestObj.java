package com.clearingcobwebsbackend.requestobjects;

import lombok.Data;

@Data
public class PasswordResetRequestObj {
  private String email;
  private String securityAnswer;
}
