package com.example.demo.controllers;

import com.example.demo.model.persistence.User;
import com.example.demo.model.persistence.repositories.CartRepository;
import com.example.demo.model.persistence.repositories.UserRepository;
import com.example.demo.model.requests.CreateUserRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class UserControllerTests {

    @InjectMocks
    private UserController userController;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Mock
    private UserRepository userRepository;

    @Mock
    private CartRepository cartRepository;

    @Test
    public void returnOkWhenFindById() {
        User user = new User();
        user.setId(0L);
        user.setUsername("username");
        user.setPassword("password");

        when(userRepository.findById(user.getId())).thenReturn(java.util.Optional.of(user));
        assertEquals(200, userController.findById(0L).getStatusCodeValue());
    }

    @Test
    public void returnNotFoundWhenFindById() {
        assertEquals(404, userController.findById(0L).getStatusCodeValue());
    }

    @Test
    public void returnOkWhenFindByUsername() {
        User user = new User();
        user.setId(0L);
        user.setUsername("username");
        user.setPassword("password");

        when(userRepository.findByUsername(user.getUsername())).thenReturn(user);
        assertEquals(200, userController.findByUserName("username").getStatusCodeValue());
    }

    @Test
    public void returnNotFoundWhenFindByUsername() {
        assertEquals(404, userController.findByUserName("username").getStatusCodeValue());
    }

    @Test
    public void returnBadRequestWhenCreateUserAndUncorfirmedPassword() {
        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setUsername("username");
        createUserRequest.setPassword("password");
        createUserRequest.setConfirmPassword("password1");

        assertEquals(400, userController.createUser(createUserRequest).getStatusCodeValue());
    }

    @Test
    public void returnOkWhenCreateUserSuccessfull() {
        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setUsername("username");
        createUserRequest.setPassword("password");
        createUserRequest.setConfirmPassword("password");

        when(bCryptPasswordEncoder.encode(createUserRequest.getPassword())).thenReturn("HASHED_PASSWORD");

        assertEquals(200, userController.createUser(createUserRequest).getStatusCodeValue());
    }

}
