package org.example.services;

import org.example.dtos.request.LoginUserRequest;
import org.example.dtos.request.UserRegistrationRequest;
import org.example.dtos.response.LoginUserResponse;
import org.example.dtos.response.UserRegistrationResponse;
import org.example.exceptions.UserNameAlreadyExist;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    UserRegistrationResponse register(UserRegistrationRequest request) throws UserNameAlreadyExist;
    LoginUserResponse login(LoginUserRequest request);

}
