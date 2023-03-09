package com.revature.repositories;

import com.revature.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * Finds a user using their email address
     * @param email The email address of the desired user
     * @return An optional that contains the user if found, otherwise an empty optional
     */
    Optional<User> findByEmail(String email);
}
