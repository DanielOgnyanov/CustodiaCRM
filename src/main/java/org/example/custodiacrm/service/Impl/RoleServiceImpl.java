package org.example.custodiacrm.service.Impl;

import org.example.custodiacrm.models.entities.Role;
import org.example.custodiacrm.models.enums.UserRole;
import org.example.custodiacrm.repositories.RoleRepository;
import org.example.custodiacrm.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void InitRoleInDb() {

        if (roleRepository.count() == 0) {
            Arrays.stream(UserRole.values()).forEach(currentRole -> {
                Role role = new Role();
                role.setName(currentRole);
                roleRepository.save(role);
            });
        }

    }



    @Override
    public Role findByRoleName(String roleName) {
        UserRole roleEnum = UserRole.valueOf(roleName);
        return roleRepository.findByName(roleEnum)
                .orElse(null);
    }
}
