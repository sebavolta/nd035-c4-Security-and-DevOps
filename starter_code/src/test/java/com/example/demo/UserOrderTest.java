package com.example.demo;

import com.example.demo.model.persistence.User;
import com.example.demo.model.persistence.UserOrder;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserOrderTest {

    @Test
    public void setUserOrderData() {
        User user = new User();
        UserOrder userOrder = new UserOrder();

        userOrder.setUser(user);

        BigDecimal total = new BigDecimal(98);
        userOrder.setTotal(total);

        userOrder.setId(3L);

        Assertions.assertEquals(3, userOrder.getId());
        Assertions.assertNotNull(userOrder.getTotal());
        Assertions.assertNotNull(userOrder.getUser());
    }
}
