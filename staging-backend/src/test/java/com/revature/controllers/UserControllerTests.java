package com.revature.controllers;

import com.revature.dtos.LoginRequest;
import com.revature.dtos.RegisterRequest;
import com.revature.exceptions.EmailTakenException;
import com.revature.exceptions.InvalidLoginException;
import com.revature.models.User;
import com.revature.repositories.UserRepository;
import com.revature.services.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class UserControllerTests {
    UserController sut;

    @Mock
    UserService serviceMock;

    @BeforeAll
    public static void beforeAll() {
        System.out.println("Starting UserController tests...");
    }

    @AfterAll
    public static void afterAll() {
        System.out.println("UserController Tests complete.");
    }

    @BeforeEach
    public void beforeEach(){
        sut = new UserController(serviceMock);
    }

    @Test
    public void testRegisterNewAccount() throws EmailTakenException {
        RegisterRequest request = new RegisterRequest("test@email.com", "password", "test", "user", "country");
        User response = new User(1, "test@email.com", "password", "test", "user", "country");

        Mockito.when(serviceMock.register(any(User.class))).thenReturn(response);

        ResponseEntity<User> result = sut.register(request);

        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        Assertions.assertEquals(response, result.getBody());
    }

    @Test
    public void testRegisterExistingAccount() throws EmailTakenException {
        RegisterRequest request = new RegisterRequest("existing@email.com", "password", "test", "user", "country");

        Mockito.when(serviceMock.register(any(User.class))).thenThrow(new EmailTakenException("A user with that Email already exists"));

        EmailTakenException ex = Assertions.assertThrows(EmailTakenException.class, ()->{sut.register(request);});

        Assertions.assertEquals("A user with that Email already exists", ex.getMessage());
    }

    @Test
    public void testLoginValidUser(@Mock HttpServletResponse response) throws InvalidLoginException {
        LoginRequest request = new LoginRequest("valid@email.com", "password");
        User returnedUser = new User(1, "valid@email.com", "password", "first", "last", "country");
        Cookie responseCookie = new Cookie("user", "1-country");

        Mockito.when(serviceMock.login(request.getEmail(), request.getPassword())).thenReturn(Optional.of(returnedUser));

        ResponseEntity<User> result = sut.login(request, response);

        verify(response).addCookie(ArgumentMatchers.any(Cookie.class));
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        Assertions.assertEquals(returnedUser, result.getBody());
    }

    @Test
    public void testLoginInvalidPassword(@Mock HttpServletResponse response) throws InvalidLoginException {
        LoginRequest request = new LoginRequest("valid@email.com", "wrongPassword");

        Mockito.when(serviceMock.login(request.getEmail(), request.getPassword())).thenThrow(new InvalidLoginException("Password is incorrect"));

        InvalidLoginException ex = Assertions.assertThrows(InvalidLoginException.class, ()->{sut.login(request, response);});

        Assertions.assertEquals("Password is incorrect", ex.getMessage());
    }

    @Test
    public void testLoginInvalidemail(@Mock HttpServletResponse response) throws InvalidLoginException {
        LoginRequest request = new LoginRequest("invalid@email.com", "password");

        Mockito.when(serviceMock.login(request.getEmail(), request.getPassword())).thenThrow(new InvalidLoginException("No user with that email found"));

        InvalidLoginException ex = Assertions.assertThrows(InvalidLoginException.class, ()->{sut.login(request, response);});

        Assertions.assertEquals("No user with that email found", ex.getMessage());
    }

}
