package com.clearingcobwebsbackend.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "users")
public class UserEntity {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "first_name", nullable = false)
  @Size(min = 2, max = 20, message = "minimum of 2 characters")
  private String firstName;

  @Column(name = "last_name", nullable = false)
  @Size(min = 2, max = 20, message = "minimum of 2 characters")
  private String lastName;

  @Column(name = "email", nullable = false, unique = true)
  @Email(message = "valid email required")
  private String email;

  @Column(name = "password", nullable = false)
  @Size(min = 8, message = "minimum of 8 characters")
  private String password;

}
