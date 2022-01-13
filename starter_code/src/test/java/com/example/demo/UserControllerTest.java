package com.example.demo;

import com.example.demo.controllers.UserController;
import com.example.demo.model.persistence.Cart;
import com.example.demo.model.persistence.User;
import com.example.demo.model.persistence.repositories.CartRepository;
import com.example.demo.model.persistence.repositories.UserRepository;
import com.example.demo.model.requests.CreateUserRequest;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {

    @Autowired
    UserController userController;

    @Mock
    UserRepository userRepository;


    @Before
    public void setUp() {
        this.userRepository = mock(UserRepository.class);
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void createUserTest() {
        CreateUserRequest userRequest = new CreateUserRequest();
        userRequest.setUsername("testuser");
        userRequest.setPassword("test1234");
        userRequest.setConfirmPassword("test1234");

        ResponseEntity<User> response = this.userController.createUser(userRequest);

        Assertions.assertNotNull(response);
        Assertions.assertEquals(200, response.getStatusCodeValue());

        User user = response.getBody();
        Assertions.assertNotNull(user);
        Assertions.assertEquals("testuser", user.getUsername());
    }

    @Test
    public void createUserWrongPasswordTest() {
        CreateUserRequest userRequest = new CreateUserRequest();
        userRequest.setUsername("someuser");
        userRequest.setPassword("short");
        userRequest.setConfirmPassword("short");

        ResponseEntity<User> response = this.userController.createUser(userRequest);

        Assertions.assertEquals(400, response.getStatusCodeValue());
    }

    @Test
    public void findByUserNameNotFoundTest() {
        ResponseEntity<User> response = this.userController.findByUserName("testuser");

        Assertions.assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    public void findByUserNameTest() {
        CreateUserRequest userRequest = new CreateUserRequest();
        userRequest.setUsername("someuser");
        userRequest.setPassword("test1234");
        userRequest.setConfirmPassword("test1234");

        this.userController.createUser(userRequest);

        ResponseEntity<User> response = this.userController.findByUserName("someuser");

        System.out.println(response.getBody());

        Assertions.assertNotNull(response);
        Assertions.assertEquals(200, response.getStatusCodeValue());
    }

    @Test
    public void findByUserIdNotFoundTest() {
        ResponseEntity<User> response = this.userController.findById(555L);

        Assertions.assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    public void findByUserIdTest() {
        CreateUserRequest userRequest = new CreateUserRequest();
        userRequest.setUsername("another");
        userRequest.setPassword("test1234");
        userRequest.setConfirmPassword("test1234");

        ResponseEntity<User> responseUser = this.userController.createUser(userRequest);


        ResponseEntity<User> response = this.userController.findById(responseUser.getBody().getId());

        Assertions.assertEquals(200, response.getStatusCodeValue());
        Assertions.assertEquals("another", response.getBody().getUsername());
    }
}
