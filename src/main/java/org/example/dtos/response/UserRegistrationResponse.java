package org.example.dtos.response;

import lombok.Data;

import java.util.UUID;

@Data
public class UserRegistrationResponse {

    private UUID id;
    private String message;

}
