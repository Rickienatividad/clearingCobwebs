package com.clearingcobwebsbackend.requestobjects;

import lombok.Data;

@Data
public class CheckResetTokenRequestObj {
  String email;
  String resetToken;
}
