package com.example.demo;

import com.example.demo.model.persistence.Item;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemTest {

    @Test
    public void setItemDataTest() {
        Item item = new Item();
        item.setId(1L);
        item.setName("Name");
        item.setDescription("Description");

        BigDecimal price = new BigDecimal(40);
        item.setPrice(price);

        Assertions.assertNotNull(item.getPrice());
        Assertions.assertNotNull(item.getId());
        Assertions.assertNotNull(item.getName());
        Assertions.assertNotNull(item.getDescription());
    }
}
