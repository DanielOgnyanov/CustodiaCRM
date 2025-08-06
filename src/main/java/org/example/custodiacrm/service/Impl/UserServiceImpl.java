package org.example.custodiacrm.service.Impl;

import org.example.custodiacrm.config.JwtUtil;
import org.example.custodiacrm.exceptions.ResourceConflictException;
import org.example.custodiacrm.exceptions.ResourceNotFoundException;
import org.example.custodiacrm.models.dto.GetUsersDTO;
import org.example.custodiacrm.models.dto.LoginRequestDTO;
import org.example.custodiacrm.models.dto.LoginResponseDTO;
import org.example.custodiacrm.models.dto.UserRegisterDTO;
import org.example.custodiacrm.models.entities.Role;
import org.example.custodiacrm.models.entities.User;
import org.example.custodiacrm.models.enums.UserRole;
import org.example.custodiacrm.repositories.RoleRepository;
import org.example.custodiacrm.repositories.UserRepository;
import org.example.custodiacrm.service.UserService;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.jwtUtil = jwtUtil;
    }




    @Override
    public void createInitialUser() {
        if (!userRepository.existsByEmail("admin@example.com")) {

            User user = User.builder()
                    .username("admin")
                    .firstName("System")
                    .lastName("Admin")
                    .email("admin@example.com")
                    .password(passwordEncoder.encode("admin123"))
                    .role(UserRole.ADMIN)
                    .phoneNumber("+1234567890")
                    .build();


            userRepository.save(user);
        }
    }

    @Override
    public void register(UserRegisterDTO userRegisterDto) {
        if (userRepository.existsByEmail(userRegisterDto.getEmail())) {
            throw new ResourceConflictException("Email already exists");
        }

        if (userRepository.existsByUsername(userRegisterDto.getUsername())) {
            throw new ResourceConflictException("Username already exists");
        }

        Role userRole = roleRepository.findByName(UserRole.USER)
                .orElseThrow(() -> new ResourceNotFoundException("Default USER role not found in database"));

        User user = User.builder()
                .username(userRegisterDto.getUsername())
                .firstName(userRegisterDto.getFirstName())
                .lastName(userRegisterDto.getLastName())
                .email(userRegisterDto.getEmail())
                .phoneNumber(userRegisterDto.getPhoneNumber())
                .password(passwordEncoder.encode(userRegisterDto.getPassword()))
                .role(userRole.getName())
                .build();

        userRepository.save(user);


    }

    @Override
    public LoginResponseDTO login(LoginRequestDTO request) {
        Optional<User> userOpt = userRepository.findByEmail(request.getEmail());

        if (userOpt.isEmpty()) {
            throw new ResourceNotFoundException("Invalid email or password");
        }

        User user = userOpt.get();

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new ResourceConflictException("Invalid email or password");
        }

        String token = jwtUtil.generateToken(user.getEmail());

        return new LoginResponseDTO(token, user.getUsername(), user.getRole().toString());
    }

    @Override
    public List<GetUsersDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> GetUsersDTO.builder()
                        .id(user.getId())
                        .username(user.getUsername())
                        .firstName(user.getFirstName())
                        .lastName(user.getLastName())
                        .email(user.getEmail())
                        .role(user.getRole().name())
                        .phoneNumber(user.getPhoneNumber())
                        .createdAt(user.getCreatedAt())
                        .build())
                .toList();
    }


}
