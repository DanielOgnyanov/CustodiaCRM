package org.example.custodiacrm.services;

import org.example.custodiacrm.config.JwtUtil;
import org.example.custodiacrm.models.dto.LoginRequestDTO;
import org.example.custodiacrm.models.dto.LoginResponseDTO;
import org.example.custodiacrm.models.dto.UserRegisterDTO;
import org.example.custodiacrm.models.entities.Role;
import org.example.custodiacrm.models.entities.User;
import org.example.custodiacrm.models.enums.UserRole;
import org.example.custodiacrm.repositories.RoleRepository;
import org.example.custodiacrm.repositories.UserRepository;
import org.example.custodiacrm.service.Impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class UserServiceImplTest {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private JwtUtil jwtUtil;
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        userRepository = mock(UserRepository.class);
        roleRepository = mock(RoleRepository.class);
        passwordEncoder = mock(PasswordEncoder.class);
        jwtUtil = mock(JwtUtil.class);
        userService = new UserServiceImpl(userRepository, passwordEncoder, roleRepository, jwtUtil);
    }

    @Test
    void createInitialUser_shouldCreateAdminIfNotExists() {
        when(userRepository.existsByEmail("admin@example.com")).thenReturn(false);
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");

        userService.createInitialUser();

        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(captor.capture());
        assertEquals("admin", captor.getValue().getUsername());
        assertEquals("admin@example.com", captor.getValue().getEmail());
    }

    @Test
    void register_shouldSaveUserWhenValid() {
        UserRegisterDTO dto = new UserRegisterDTO();
        dto.setUsername("john");
        dto.setFirstName("John");
        dto.setLastName("Doe");
        dto.setEmail("john@example.com");
        dto.setPhoneNumber("+123456789");
        dto.setPassword("password");

        when(userRepository.existsByEmail(dto.getEmail())).thenReturn(false);
        when(userRepository.existsByUsername(dto.getUsername())).thenReturn(false);
        when(passwordEncoder.encode(dto.getPassword())).thenReturn("encodedPwd");
        when(roleRepository.findByName(UserRole.USER)).thenReturn(Optional.of(new Role(1L, UserRole.USER)));

        userService.register(dto);

        verify(userRepository).save(any(User.class));
    }

    @Test
    void login_shouldReturnTokenWhenCredentialsValid() {
        LoginRequestDTO request = new LoginRequestDTO();
        request.setEmail("john@example.com");
        request.setPassword("password");

        User user = User.builder()
                .email("john@example.com")
                .username("john")
                .password("encodedPwd")
                .role(UserRole.USER)
                .build();

        when(userRepository.findByEmail(request.getEmail())).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(request.getPassword(), user.getPassword())).thenReturn(true);
        when(jwtUtil.generateToken(user.getEmail())).thenReturn("mockJwtToken");

        LoginResponseDTO response = userService.login(request);

        assertEquals("mockJwtToken", response.getToken());
        assertEquals("john", response.getUsername());
        assertEquals("USER", response.getUserRole());
    }

}
