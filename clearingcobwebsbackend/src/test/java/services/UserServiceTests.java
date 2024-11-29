package services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import entities.UserEntity;
import models.AppUser;
import repositories.UserRepository;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class UserServiceTests {

  @Mock
  private UserRepository userRepository;

  @InjectMocks
  private UserService userService;

  @Test
  public void testThatUserIsSaved() {
    AppUser appUser = AppUser.builder()
        .firstName("test")
        .lastName("test")
        .email("test@test.com")
        .password("password")
        .build();

    UserEntity newUser = userService.createUser(appUser);
    Assertions.assertThat(newUser.getEmail()).isEqualTo("test@test.com");

  }
}
