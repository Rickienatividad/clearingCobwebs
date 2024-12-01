package services;

import java.util.List;

import org.springframework.stereotype.Service;

import entities.UserEntity;
import lombok.RequiredArgsConstructor;
import mapper.ProjectMapper;
import models.AppUser;
import repositories.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepository;
  private final ProjectMapper projectMapper;

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

  public List<UserEntity> findUserEntities() {
    List<UserEntity> allUsers = userRepository.findAll();
    return allUsers;
  }
}
