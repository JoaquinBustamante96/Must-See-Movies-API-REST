package com.first.demoMongo.repositories;

import com.first.demoMongo.dataServices.DatabaseSeederService;
import com.first.demoMongo.documents.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserIT {

    @Autowired
    UserRepository userRepository;

    @Test
    public void findByusername(){
        String username= "usernameTest";
        User user = this.userRepository.findByusername(username);
        assertNotNull(user);
        assertTrue(user.getUsername().equals(user));
    }

    @Test
    public void findBydni(){
        String dni = "33333";
        User user = this.userRepository.findBydni(dni);
        assertNotNull(user);
        assertEquals(user.getDni(),dni);
    }

}
