package org.example.custodiacrm.service;

import org.example.custodiacrm.models.entities.Role;

public interface RoleService {

    void InitRoleInDb();


    Role findByRoleName(String roleName);
}
