package org.example.custodiacrm.models.dto;


import lombok.Data;

@Data
public class UserRegisterDTO {

    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String password;
}
