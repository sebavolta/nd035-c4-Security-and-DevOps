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

    private UserController userController;

    @Mock
    UserRepository userRepository;

    @Mock
    CartRepository cartRepository;

    @Mock
    BCryptPasswordEncoder encoder;

    @Before
    public void setUp() {
        this.userController = new UserController();
        //MockitoAnnotations.initMocks(this);
    }

    @Rule
    public MockitoRule rule = MockitoJUnit.rule();

    @Test
    public void createUserTest() {
        when(this.encoder.encode("test1234")).thenReturn("asdasdsaasd154546");
        when(cartRepository.save(new Cart())).thenReturn(new Cart());

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
        Assertions.assertEquals("asdasdsaasd154546", user.getPassword());
    }
}
