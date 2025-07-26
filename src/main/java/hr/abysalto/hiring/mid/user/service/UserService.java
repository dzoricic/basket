package hr.abysalto.hiring.mid.user.service;

import hr.abysalto.hiring.mid.user.mapper.UserMapper;
import hr.abysalto.hiring.mid.user.model.RegistrationRequest;
import hr.abysalto.hiring.mid.user.model.UserDto;
import hr.abysalto.hiring.mid.user.model.UserEntity;
import hr.abysalto.hiring.mid.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return UserMapper.authDetailsFromEntity(getUserEntityByUsername(username));
    }

    public UserDetails registerUser(RegistrationRequest request) {
        log.info("Registering user {}", request.getUsername());
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new IllegalArgumentException("Username is already in use.");
        }
        var entity = UserMapper.toEntity(request);
        var encodedPassword = passwordEncoder.encode(request.getPassword());
        entity.setPassword(encodedPassword);
        return UserMapper.authDetailsFromEntity(userRepository.save(entity));
    }

    public UserDto getUserByUsername(String username) {
        return UserMapper.fromEntity(getUserEntityByUsername(username));
    }

    private UserEntity getUserEntityByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found."));
    }
}
