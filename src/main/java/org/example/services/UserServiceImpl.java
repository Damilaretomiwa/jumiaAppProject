package org.example.services;

import lombok.AllArgsConstructor;
import org.example.data.models.User;
import org.example.data.repositories.UserRepository;
import org.example.dtos.request.LoginUserRequest;
import org.example.dtos.request.UserRegistrationRequest;
import org.example.dtos.response.LoginUserResponse;
import org.example.dtos.response.UserRegistrationResponse;
import org.example.exceptions.UserNameAlreadyExist;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static org.example.utils.ExceptionUtils.USER_WITH_USERNAME_ALREADY_EXIST;
import static org.example.utils.ResponseUtils.USER_REGISTRATION_SUCCESSFUL;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;
    private ModelMapper modelMapper;


    @Override
    public UserRegistrationResponse register(UserRegistrationRequest request) throws UserNameAlreadyExist {
        User user = new User();
        modelMapper.map(request,user);
        if (userWithUserNameAlreadyExist(user.getUsername(), user.getPhoneNumber(), user.getEmailAddress())) throw new UserNameAlreadyExist(
                USER_WITH_USERNAME_ALREADY_EXIST);
        User savedUser  = userRepository.save(user);
        return buildUserRegistrationResponse(savedUser.getId());
    }

    private UserRegistrationResponse buildUserRegistrationResponse(UUID id) {
        UserRegistrationResponse response = new UserRegistrationResponse();
        response.setMessage(USER_REGISTRATION_SUCCESSFUL);
        response.setId(id);
        return response;
    }

    private boolean userWithUserNameAlreadyExist(String username, String phoneNumber, String email) {
       User user = UserRepository.findByUsername(username);
       User user1 =  userRepository.findByPhoneNumber(phoneNumber);
       User user2 = userRepository.findByEmailAddress(email);
        return user != null || user1 != null || user2 != null;
    }

    @Override
    public LoginUserResponse login(LoginUserRequest request) {
        User user = UserRepository.findByUsername(request.getUsername());
        if (user != null && user.getPassword().equals(request.getPassword())) {
            LoginUserResponse response = new LoginUserResponse();
            response.setMessage("Login successful!");
            response.setId(user.getId());
            return response;
        }
        else {
            LoginUserResponse response = new LoginUserResponse();
            response.setMessage("Invalid username or password.");
            return response;
        }



    }

}
