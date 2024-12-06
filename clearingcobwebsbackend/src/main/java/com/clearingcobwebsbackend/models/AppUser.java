package com.clearingcobwebsbackend.models;

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
public class AppUser {

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

}
