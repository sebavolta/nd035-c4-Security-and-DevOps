package com.example.demo;

import com.example.demo.controllers.ItemController;
import com.example.demo.model.persistence.Item;
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
public class ItemControllerTest {

    @Autowired
    ItemController itemController;

    @Test
    public void getItemsTest() {
        ResponseEntity<List<Item>> response = this.itemController.getItems();

        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getBody());
    }

    @Test
    public void getItemByIdTest() {
        ResponseEntity<Item> response = this.itemController.getItemById(1L);

        Assertions.assertNotNull(response.getBody());
    }

    @Test
    public void getItemByNameTest() {
        ResponseEntity<List<Item>> response = this.itemController.getItemsByName("Round Widget");

        Assertions.assertNotNull(response.getBody());
    }
}
