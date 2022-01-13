package com.example.demo;

import com.example.demo.controllers.CartController;
import com.example.demo.controllers.UserController;
import com.example.demo.model.persistence.Cart;
import com.example.demo.model.persistence.User;
import com.example.demo.model.requests.CreateUserRequest;
import com.example.demo.model.requests.ModifyCartRequest;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CartControllerTest {

    @Autowired
    CartController cartController;

    @Autowired
    UserController userController;

    @Test
    public void addToCartUserNotFoundTest() {
        ModifyCartRequest request = new ModifyCartRequest();
        request.setItemId(1);
        request.setUsername("johhndoe");
        request.setQuantity(1);

        ResponseEntity<Cart> response = this.cartController.addTocart(request);

        Assertions.assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    public void addToCartItemNotFoundTest() {
        CreateUserRequest userRequest = new CreateUserRequest();
        userRequest.setUsername("userforcart");
        userRequest.setPassword("test1234");
        userRequest.setConfirmPassword("test1234");

        this.userController.createUser(userRequest);

        ModifyCartRequest request = new ModifyCartRequest();
        request.setItemId(55);
        request.setUsername("userforcart");
        request.setQuantity(1);

        ResponseEntity<Cart> response = this.cartController.addTocart(request);

        Assertions.assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    public void addToCartTest() {
        CreateUserRequest userRequest = new CreateUserRequest();
        userRequest.setUsername("anotheruserforcart");
        userRequest.setPassword("test1234");
        userRequest.setConfirmPassword("test1234");

        this.userController.createUser(userRequest);

        ModifyCartRequest request = new ModifyCartRequest();
        request.setItemId(1);
        request.setUsername("anotheruserforcart");
        request.setQuantity(2);

        ResponseEntity<Cart> response = this.cartController.addTocart(request);

        Assertions.assertEquals(200, response.getStatusCodeValue());
        Assertions.assertNotNull(response.getBody().getItems());
    }

    @Test
    public void removeFromCartUserNotFoundTest() {
        ModifyCartRequest request = new ModifyCartRequest();
        request.setItemId(1);
        request.setUsername("johhndoe");
        request.setQuantity(1);

        ResponseEntity<Cart> response = this.cartController.removeFromcart(request);

        Assertions.assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    public void removeFromCartItemNotFoundTest() {
        CreateUserRequest userRequest = new CreateUserRequest();
        userRequest.setUsername("userforremovecart");
        userRequest.setPassword("test1234");
        userRequest.setConfirmPassword("test1234");

        this.userController.createUser(userRequest);

        ModifyCartRequest request = new ModifyCartRequest();
        request.setItemId(55);
        request.setUsername("userforremovecart");
        request.setQuantity(1);

        ResponseEntity<Cart> response = this.cartController.removeFromcart(request);

        Assertions.assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    public void removeFromCartTest() {
        CreateUserRequest userRequest = new CreateUserRequest();
        userRequest.setUsername("removeanotheruserforcart");
        userRequest.setPassword("test1234");
        userRequest.setConfirmPassword("test1234");

        this.userController.createUser(userRequest);

        ModifyCartRequest request = new ModifyCartRequest();
        request.setItemId(1);
        request.setUsername("removeanotheruserforcart");
        request.setQuantity(2);

        this.cartController.addTocart(request);

        ResponseEntity<Cart> removedResponse = this.cartController.removeFromcart(request);

        Assertions.assertEquals(200, removedResponse.getStatusCodeValue());
        Assertions.assertTrue(removedResponse.getBody().getItems().isEmpty());
    }
}
