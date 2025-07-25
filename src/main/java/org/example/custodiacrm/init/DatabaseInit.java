package org.example.custodiacrm.init;


import jakarta.annotation.PostConstruct;
import org.example.custodiacrm.service.RoleService;
import org.example.custodiacrm.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class DatabaseInit {

    private final RoleService roleService;
    private final UserService userService;

    public DatabaseInit(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @PostConstruct
    public void init() {
        roleService.InitRoleInDb();
        userService.createInitialUser();
    }
}
