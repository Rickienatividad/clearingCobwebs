package services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockingDetails;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.http.ResponseEntity;

import com.clearingcobwebsbackend.entities.UserEntity;
import com.clearingcobwebsbackend.models.AppUser;
import com.clearingcobwebsbackend.repositories.UserRepository;
import com.clearingcobwebsbackend.requestobjects.UserRequestObj;
import com.clearingcobwebsbackend.services.UserService;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class UserServiceTests {

  @Mock
  private UserRepository userRepository;

  @InjectMocks
  private UserService userService;

  /*
   * @Test
   * public ResponseEntity<?> testThatUserIsSavedandReturnsUserEntity() {
   * UserRequestObj userRequestObj = UserRequestObj.builder()
   * .firstName("test")
   * .lastName("test")
   * .email("test@test.com")
   * .password("password")
   * .build();
   * 
   * UserEntity newUser = userService.createUser(userRequestObj);
   * Assertions.assertThat(newUser.getEmail()).isEqualTo("test@test.com");
   * MockingDetails mockingDetails = Mockito.mockingDetails(newUser);
   * Class<?> whichClass = mockingDetails.getMock().getClass();
   * assertEquals(whichClass.getName(),
   * "com.clearingcobwebsbackend.entities.UserEntity");
   * }
   */
}
