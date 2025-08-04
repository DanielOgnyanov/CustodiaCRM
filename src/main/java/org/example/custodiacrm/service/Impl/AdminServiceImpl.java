package org.example.custodiacrm.service.Impl;

import org.example.custodiacrm.models.dto.ChangeUserRoleDTO;

import org.example.custodiacrm.models.entities.User;
import org.example.custodiacrm.models.enums.UserRole;
import org.example.custodiacrm.repositories.UserRepository;
import org.example.custodiacrm.service.AdminService;
import org.springframework.stereotype.Service;


@Service
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;

    public AdminServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public void changeUserRole(ChangeUserRoleDTO changeUserRoleDTO) {

        User user = userRepository.findByUsername(changeUserRoleDTO.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        UserRole newRole;
        try {
            newRole = UserRole.valueOf(changeUserRoleDTO.getNewRole().toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid role: " + changeUserRoleDTO.getNewRole());
        }

        user.setRole(user.getRole());
        userRepository.save(user);

    }
}
