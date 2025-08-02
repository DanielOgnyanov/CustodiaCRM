package org.example.custodiacrm.service;

import org.example.custodiacrm.models.dto.LoginRequestDTO;
import org.example.custodiacrm.models.dto.LoginResponseDTO;
import org.example.custodiacrm.models.dto.UserRegisterDTO;

public interface UserService {

    void createInitialUser();

    void register(UserRegisterDTO userRegisterDto);

    LoginResponseDTO login(LoginRequestDTO request);

}
