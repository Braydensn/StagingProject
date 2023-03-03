package com.revature.services;

import com.revature.exceptions.EmailTakenException;
import com.revature.exceptions.InvalidLoginException;
import com.revature.models.User;
import com.revature.repositories.UserRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {

    public UserService sut;

    @Mock
    UserRepository repoMock;

    @BeforeAll
    public static void beforeAll() {
        System.out.println("Starting UserService tests...");
    }

    @AfterAll
    public static void afterAll() {
        System.out.println("UserService Tests complete.");
    }

    @BeforeEach
    public void beforeEach(){
        sut = new UserService(repoMock);
    }

    @Test
    public void testRegisterNewUser() throws EmailTakenException {
        //arrange the required objects
        User newUser = new User("email@mail.com", "pass", "first", "last", "country");
        User returnedUser = new User(1, "email@mail.com", "pass", "first", "last", "country");

        //mock the repo call
        Mockito.when(repoMock.save(newUser)).thenReturn(returnedUser);

        //run the test
        User result = sut.register(newUser);

        //make sure the result of the repo call gets returned
        Assertions.assertEquals(returnedUser, result);
    }

    @Test
    public void testRegisterExistingUser(){
        //arrange objects
        User existingUser = new User("email@mail.com", "pass", "first", "last", "country");

        //mock the repo call, throw an exception
        Mockito.when(repoMock.save(existingUser)).thenThrow(new DataIntegrityViolationException("duplicate key value violates unique constraint \"uk_6dotkott2kjsp8vw4d0m25fb7\""));

        //assert that the method will throw an exception
        EmailTakenException ex = Assertions.assertThrows(EmailTakenException.class, ()->{sut.register(existingUser);});

        //make sure the error message is what we expect
        Assertions.assertEquals("A user with that Email already exists", ex.getMessage());

    }

    @Test
    public void testLoginValidUser() throws InvalidLoginException {
        //arrange the objects
        String email = "valid@email.com";
        String password = "password";
        User returnedUser = new User(1, "valid@email.com", "password", "first", "last", "country");

        //mock the repo call
        Mockito.when(repoMock.findByEmail(email)).thenReturn(Optional.of(returnedUser));

        //run test
        Optional<User> result = sut.login(email, password);

        //make sure the result of the repo call gets returned
        Assertions.assertEquals(Optional.of(returnedUser), result);
    }

    @Test
    public void testLoginInvalidPassword(){
        //arrange the objects
        String email = "valid@email.com";
        String password = "invalidPassword";
        User returnedUser = new User(1, "valid@email.com", "password", "first", "last", "country");

        //mock the repo call
        Mockito.when(repoMock.findByEmail(email)).thenReturn(Optional.of(returnedUser));

        //assert that the method will throw an exception
        InvalidLoginException ex = Assertions.assertThrows(InvalidLoginException.class, ()->{sut.login(email, password);});

        //make sure the error message is what we expect
        Assertions.assertEquals("Password is incorrect", ex.getMessage());
    }

    @Test
    public void testLoginInvalidEmail(){
        //arrange the objects
        String email = "invalid@email.com";
        String password = "password";

        //mock the repo call, return nothing
        Mockito.when(repoMock.findByEmail(email)).thenReturn(Optional.empty());

        //assert that the method will throw an exception
        InvalidLoginException ex = Assertions.assertThrows(InvalidLoginException.class, ()->{sut.login(email, password);});

        //make sure the error message is what we expect
        Assertions.assertEquals("No user with that email found", ex.getMessage());
    }


}
