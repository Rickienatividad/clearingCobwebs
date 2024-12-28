package com.clearingcobwebsbackend.requestobjects;

import lombok.Data;

@Data
public class LoginRequestObj {
  private String email;
  private String password;
}
