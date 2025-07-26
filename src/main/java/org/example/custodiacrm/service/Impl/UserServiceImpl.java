package org.example.custodiacrm.service.Impl;

import org.example.custodiacrm.models.entities.User;
import org.example.custodiacrm.models.enums.UserRole;
import org.example.custodiacrm.repositories.UserRepository;
import org.example.custodiacrm.service.UserService;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
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



}
