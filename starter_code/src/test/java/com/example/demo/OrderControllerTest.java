package com.example.demo;

import com.example.demo.controllers.OrderController;
import com.example.demo.controllers.UserController;
import com.example.demo.model.persistence.UserOrder;
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

    @Test
    public void submitOrderNullUserTest() {
        ResponseEntity<UserOrder> response = this.orderController.submit("anyuser");

        Assertions.assertEquals(404, response.getStatusCodeValue());
    }

    @Test
    public void historyOrderNullUserTest() {
        ResponseEntity<List<UserOrder>> response = this.orderController.getOrdersForUser("anyuser");

        Assertions.assertEquals(404, response.getStatusCodeValue());
    }
}
