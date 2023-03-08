package com.revature.controllers;


import com.revature.dtos.LoginRequest;
import com.revature.dtos.RegisterRequest;
import com.revature.exceptions.EmailTakenException;
import com.revature.exceptions.InvalidLoginException;
import com.revature.models.User;
import com.revature.services.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:3000"}, allowCredentials = "true", exposedHeaders = "Authorization")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody RegisterRequest register) throws EmailTakenException {
        User newUser = new User(register.getEmail(),
                register.getPassword(),
                register.getFirstName(),
                register.getLastName(),
                register.getCountry());

        User returnedUser = userService.register(newUser);
        return ResponseEntity.ok(returnedUser);
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody LoginRequest login, HttpServletResponse response) throws InvalidLoginException {
        Optional<User> user = userService.login(login.getEmail(), login.getPassword());
        if(user.isPresent()) {
            Cookie userCookie = new Cookie("user", user.get().getId().toString() + "-" + user.get().getCountry());
            userCookie.setMaxAge(3600);
            userCookie.setPath("/");
            response.addCookie(userCookie);
            return ResponseEntity.ok(user.get());
        }
        return ResponseEntity.badRequest().build();
    }
}
