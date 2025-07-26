package org.example.custodiacrm.service;

import org.example.custodiacrm.models.dto.UserRegisterDto;

public interface UserService {

    void createInitialUser();

    void register(UserRegisterDto userRegisterDto);
}
