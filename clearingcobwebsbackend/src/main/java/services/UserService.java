package services;

import org.springframework.stereotype.Service;

import entities.UserEntity;
import lombok.RequiredArgsConstructor;
import models.AppUser;
import repositories.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepository;

  public UserEntity createUser(AppUser appUser) {
    UserEntity newUser = UserEntity.builder()
        .firstName(appUser.getFirstName())
        .lastName(appUser.getLastName())
        .email(appUser.getEmail())
        .password(appUser.getPassword())
        .build();

    userRepository.save(newUser);
    return newUser;
  }
}
