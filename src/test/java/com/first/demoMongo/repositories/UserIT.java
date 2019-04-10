package com.first.demoMongo.repositories;

import com.first.demoMongo.documents.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:test.properties")
public class UserIT {

    @Autowired
    UserRepository userRepository;

    @Test
    public void findByusername(){
        String username= "usernameTest";
        User user = this.userRepository.findByusername(username);
        assertNotNull(user);
        assertEquals(user.getUsername(), username);
    }


    @Test
    public void findByemail(){
        String email = "email@prueba.com";
        User user = this.userRepository.findByemail(email);
        assertNotNull(user);
        assertEquals(user.getEmail(),email);
    }

}
