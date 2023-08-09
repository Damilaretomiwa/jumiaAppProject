package org.example.controllers;


import lombok.AllArgsConstructor;
import org.example.dtos.request.LoginUserRequest;
import org.example.dtos.request.UserRegistrationRequest;
import org.example.dtos.response.LoginUserResponse;
import org.example.exceptions.UserNameAlreadyExist;
import org.example.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/user")
public class UserController {

    private UserService userService;

    @PostMapping("/Register")
    public ResponseEntity<?> register(@RequestBody UserRegistrationRequest request){
        try {
            var response  = userService.register(request);
            return ResponseEntity.ok(response);
        }catch (UserNameAlreadyExist exception){
            String message  = exception.getMessage();
            return ResponseEntity.badRequest().body(message);


        }

    }
    @PostMapping("/Login")
    public ResponseEntity<?> login(@RequestBody LoginUserRequest request) {
        LoginUserResponse response = userService.login(request);
        if (response.getMessage().equals("Login successful!")) {
            return ResponseEntity.ok(response);

        }else {
            return ResponseEntity.status(401).body(response);
        }


    }
}
