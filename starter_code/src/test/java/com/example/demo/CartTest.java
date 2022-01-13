package com.example.demo;

import com.example.demo.model.persistence.Cart;
import com.example.demo.model.persistence.Item;
import com.example.demo.model.persistence.User;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CartTest {

    @Test
    public void setCartDataTest() {
        Cart cart = new Cart();
        cart.setId(1);
        cart.setTotal(new BigDecimal(20));
        List<Item> items = new ArrayList<>();
        cart.setItems(items);
        cart.setUser(new User());

        Assertions.assertEquals(1, cart.getId());
        Assertions.assertNotNull(cart.getItems());
        Assertions.assertNotNull(cart.getTotal());
        Assertions.assertNotNull(cart.getUser());
    }
}
