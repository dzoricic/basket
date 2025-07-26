package hr.abysalto.hiring.mid.user;

import hr.abysalto.hiring.mid.user.model.RegistrationRequest;
import hr.abysalto.hiring.mid.user.repository.UserRepository;
import hr.abysalto.hiring.mid.user.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static hr.abysalto.hiring.mid.factory.UserFactory.*;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceUnitTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @Test
    void shouldGetUserAuthDetailsGivenUsername() {
        // given
        var givenUserId = 1;
        var givenUserDetails = givenUserAuthDetails();
        var givenEntity = givenUserEntity(givenUserId);
        when(userRepository.findByUsername(givenUserDetails.getUsername())).thenReturn(Optional.of(givenEntity));

        // when
        var actual = userService.loadUserByUsername(givenUserDetails.getUsername());

        // then
        then(actual.getUsername()).isEqualTo(givenUserDetails.getUsername());
        then(actual.getPassword()).isEqualTo(givenUserDetails.getPassword());
        then(actual.getAuthorities()).hasSize(0);
    }

    @Test
    void shouldThrowExceptionWhenGettingUserAuthDetailsGivenMissingUser() {
        // given
        var givenUsername = "givenUsername";
        when(userRepository.findByUsername(givenUsername)).thenReturn(Optional.empty());

        // when
        var actual = catchThrowable(() -> userService.loadUserByUsername(givenUsername));

        // then
        then(actual).isExactlyInstanceOf(UsernameNotFoundException.class);
        then(actual.getMessage()).isEqualTo("User not found.");
    }

    @Test
    void shouldGetUserGivenUsername() {
        // given
        var givenUserId = 1;
        var givenUserDto = givenUserDto(givenUserId);
        var givenEntity = givenUserEntity(givenUserDto.id());
        when(userRepository.findByUsername(givenEntity.getUsername())).thenReturn(Optional.of(givenEntity));

        // when
        var actual = userService.getUserByUsername(givenEntity.getUsername());

        // then
        then(actual.id()).isEqualTo(givenEntity.getId());
        // this should be done by mapper test
        then(actual.username()).isEqualTo(givenEntity.getUsername());
        then(actual.firstName()).isEqualTo(givenEntity.getFirstName());
        then(actual.lastName()).isEqualTo(givenEntity.getLastName());
        then(actual.title()).isEqualTo(givenEntity.getTitle());
    }

    @Test
    void shouldThrowExceptionWhenGettingUserGivenMissingUser() {
        // given
        var givenUsername = "givenUsername";
        when(userRepository.findByUsername(givenUsername)).thenReturn(Optional.empty());

        // when
        var actual = catchThrowable(() -> userService.getUserByUsername(givenUsername));

        // then
        then(actual).isExactlyInstanceOf(UsernameNotFoundException.class);
        then(actual.getMessage()).isEqualTo("User not found.");
    }

    @Test
    void shouldRegisterNewUserGivenRegistrationRequest() {
        // given
        var givenUserId = 1;
        var givenPlaintextPassword = "plaintextPassword";
        var givenUserEntity = givenUserEntity(givenUserId);
        var registrationRequest = new RegistrationRequest(
                givenUserEntity.getFirstName(),
                givenUserEntity.getLastName(),
                givenUserEntity.getTitle(),
                givenUserEntity.getUsername(),
                givenPlaintextPassword
        );
        when(userRepository.existsByUsername(givenUserEntity.getUsername())).thenReturn(false);
        when(passwordEncoder.encode(givenPlaintextPassword)).thenReturn(givenUserEntity.getPassword());
        when(userRepository.save(any())).thenReturn(givenUserEntity);

        // when
        var actual = userService.registerUser(registrationRequest);

        // then
        then(actual.getAuthorities()).hasSize(0);
        then(actual.getUsername()).isEqualTo(givenUserEntity.getUsername());
        then(actual.getPassword()).isEqualTo(givenUserEntity.getPassword());
    }

    @Test
    void shouldThrowExceptionGivenUserAlreadyExists() {
        // given
        var givenUserId = 1;
        var givenPlaintextPassword = "plaintextPassword";
        var givenUserEntity = givenUserEntity(givenUserId);
        var registrationRequest = new RegistrationRequest(
                givenUserEntity.getFirstName(),
                givenUserEntity.getLastName(),
                givenUserEntity.getTitle(),
                givenUserEntity.getUsername(),
                givenPlaintextPassword
        );
        when(userRepository.existsByUsername(givenUserEntity.getUsername())).thenReturn(true);

        // when
        var actual = catchThrowable(() -> userService.registerUser(registrationRequest));

        // then
        then(actual).isExactlyInstanceOf(IllegalArgumentException.class);
        then(actual.getMessage()).isEqualTo("Username is already in use.");
    }
}
