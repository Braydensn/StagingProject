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

@Service
public class UserService {

    private UserRepository userRepo;

    @Autowired
    public UserService(UserRepository userRepo){
        this.userRepo = userRepo;
    }

    public User register(User user) throws EmailTakenException {
        try {
            return userRepo.save(user);
        }catch(DataIntegrityViolationException e){
            throw new EmailTakenException("A user with that Email already exists");
        }
    }

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
