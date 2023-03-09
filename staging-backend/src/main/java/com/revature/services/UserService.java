package com.revature.services;

import com.revature.exceptions.EmailTakenException;
import com.revature.exceptions.InvalidLoginException;
import com.revature.models.User;
import com.revature.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Optional;

/**
 * Contains additional logic required to handle requests related to users
 */
@Service
public class UserService {

    private UserRepository userRepo;

    @Autowired
    public UserService(UserRepository userRepo){
        this.userRepo = userRepo;
    }

    /**
     * Registers a new user into the database
     * @param user A user object with the new user's information
     * @return A copy of the newly created user
     * @throws EmailTakenException If the email of the new user is already in use be an existing user
     */
    public User register(User user) throws EmailTakenException {
        try {
            return userRepo.save(user);
        }catch(DataIntegrityViolationException e){
            throw new EmailTakenException("A user with that Email already exists");
        }
    }

    /**
     * Authenticates a user's credentials using their email and password
     * @param email The user's email address
     * @param password The user's password
     * @return An optional containing the user, if authentication was successful
     * @throws InvalidLoginException If the email or password given were invalid
     */
    public Optional<User> login(String email, String password) throws InvalidLoginException {
        Optional<User> user = userRepo.findByEmail(email);
        if(user.isPresent()){
            if(user.get().getPassword().equals(password)){
                return user;
            }
            throw new InvalidLoginException("Password is incorrect");
        }
        throw new InvalidLoginException("No user with that email found");
    }
}
