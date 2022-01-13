package com.example.demo;

import com.example.demo.controllers.CartController;
import com.example.demo.controllers.OrderController;
import com.example.demo.controllers.UserController;
import com.example.demo.model.persistence.Cart;
import com.example.demo.model.persistence.UserOrder;
import com.example.demo.model.requests.CreateUserRequest;
import com.example.demo.model.requests.ModifyCartRequest;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderControllerTest {

    @Autowired
    UserController userController;

    @Autowired
    OrderController orderController;

    @Autowired
    CartController cartController;

    @Test
    public void submitOrderNullUserTest() {
        ResponseEntity<UserOrder> response = this.orderController.submit("anyuser");

        Assertions.assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    public void submitOrderTest() {
        CreateUserRequest userRequest = new CreateUserRequest();
        userRequest.setUsername("userforrorder");
        userRequest.setPassword("test1234");
        userRequest.setConfirmPassword("test1234");

        this.userController.createUser(userRequest);

        ModifyCartRequest request = new ModifyCartRequest();
        request.setItemId(1);
        request.setUsername("userforrorder");
        request.setQuantity(1);

        this.cartController.addTocart(request);

        ResponseEntity<UserOrder> response = this.orderController.submit("userforrorder");

        Assertions.assertEquals(200, response.getStatusCodeValue());
        Assertions.assertFalse(response.getBody().getItems().isEmpty());
    }

    @Test
    public void historyOrderNullUserTest() {
        ResponseEntity<List<UserOrder>> response = this.orderController.getOrdersForUser("anyuser");

        Assertions.assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    public void historyOrderTest() {
        CreateUserRequest userRequest = new CreateUserRequest();
        userRequest.setUsername("userforrhistoryorder");
        userRequest.setPassword("test1234");
        userRequest.setConfirmPassword("test1234");

        this.userController.createUser(userRequest);

        ModifyCartRequest request = new ModifyCartRequest();
        request.setItemId(1);
        request.setUsername("userforrhistoryorder");
        request.setQuantity(1);

        this.cartController.addTocart(request);

        this.orderController.submit("userforrhistoryorder");

        ResponseEntity<List<UserOrder>> response = this.orderController.getOrdersForUser("userforrhistoryorder");

        Assertions.assertEquals(200, response.getStatusCodeValue());
        Assertions.assertFalse(response.getBody().isEmpty());
    }
}
