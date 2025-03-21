package com.clearingcobwebsbackend.requestobjects;

import lombok.Data;

@Data
public class UpdatePasswordObj {
  String email;
  String resetToken;
  String newPassword;

}
