package org.example.custodiacrm.web;


import org.example.custodiacrm.models.dto.UserRegisterDto;
import org.example.custodiacrm.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserRegisterDto userRegisterDto) {
        userService.register(userRegisterDto);
        return ResponseEntity.ok("User registered successfully");
    }
}
