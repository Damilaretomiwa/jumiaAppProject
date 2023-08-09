package org.example.dtos.request;

import jakarta.persistence.GeneratedValue;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegistrationRequest {
    private String username;
    private String emailAddress;
    private String phoneNumber;
    private String password;
}
