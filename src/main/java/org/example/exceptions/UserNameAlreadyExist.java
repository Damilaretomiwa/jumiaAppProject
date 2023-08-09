package org.example.exceptions;

public class UserNameAlreadyExist extends Exception{

    public UserNameAlreadyExist(String message){
        super(message);
    }
}
