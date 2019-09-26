package com.moviesmustsee.repositories;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:test.properties")
public class PasswordResetTokenIT {

    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    @Test
    public void findTokenByUserEmailTest() {
        assertEquals(this.passwordResetTokenRepository.findTokenByUserId("0001")
                .getToken().getValue(), "PaE8iPX3AekAaV945_ujcm7q1ik");
    }

    @Test
    public void findTokenByValueTest() {
        assertEquals(this.passwordResetTokenRepository.findTokenByValue("PaE8iPX3AekAaV945_ujcm7q1ik")
                        .getToken()
                        .getValue()
                , "PaE8iPX3AekAaV945_ujcm7q1ik");
    }


}
