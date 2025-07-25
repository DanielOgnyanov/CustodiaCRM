package org.example.custodiacrm.init;


import jakarta.annotation.PostConstruct;
import org.example.custodiacrm.service.UserService;
import org.springframework.stereotype.Component;

@Component
public class DatabaseInit {

    private final UserService userService;

    public DatabaseInit(UserService userService) {
        this.userService = userService;
    }

    @PostConstruct
    public void init() {
        userService.createInitialUser();
    }
}
