package org.example.data.repositories;

import org.example.data.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {


    static User findByUsername(String username) {
        return null;
    }


    User findByPhoneNumber(String phoneNumber);

    User findByEmailAddress(String email);
}
