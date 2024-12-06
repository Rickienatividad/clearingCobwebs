package com.clearingcobwebsbackend.services;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.clearingcobwebsbackend.entities.UserEntity;
import com.clearingcobwebsbackend.mapper.ProjectMapper;
import com.clearingcobwebsbackend.models.AppUser;
import com.clearingcobwebsbackend.repositories.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@Component
@RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepository;
  // private final ProjectMapper projectMapper;

  public UserEntity createUser(AppUser appUser) {
    UserEntity newUser = UserEntity.builder()
        .firstName(appUser.getFirstName())
        .lastName(appUser.getLastName())
        .email(appUser.getEmail())
        .password(appUser.getPassword())
        .build();

    userRepository.saveAndFlush(newUser);
    return newUser;
  }

  public List<UserEntity> findUserEntities() {
    List<UserEntity> allUsers = userRepository.findAll();
    return allUsers;
  }
}
