package com.example.demo;

import com.example.demo.model.persistence.User;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {

    @Test
    public void setUserData() {
        User user = new User();
        user.setId(3);
        user.setPassword("someweirdpassword");

        Assertions.assertEquals(3, user.getId());
        Assertions.assertEquals("someweirdpassword", user.getPassword());
    }
}
