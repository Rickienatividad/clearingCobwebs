package com.clearingcobwebsbackend.requestobjects;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequestObj {

  @NotBlank(message = "First Name Cannot Be Blank")
  @Size(min = 2, max = 20)
  private String firstName;

  @NotBlank(message = "Last Name Cannot Be Blank")
  @Size(min = 2, max = 20)
  private String lastName;

  @NotBlank(message = "Email Cannot Be Blank")
  @Email(message = "Valid email required")
  private String email;

  @NotBlank(message = "Password Cannot Be Blank")
  @Size(min = 8, max = 20)
  private String password;

  @NotBlank(message = "Password Cannot Be Blank")
  @Size(min = 8, max = 20)
  private String passwordConfirmation;

  @NotBlank(message = "A Security Question Must Be Selected")
  private String securityQuestion;

  @NotBlank(message = "Provide An Answer For The Chosen Security Question")
  private String securityAnswer;
}
