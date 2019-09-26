package com.moviesmustsee.businessServices;

import com.moviesmustsee.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticatedUser {

    @Autowired
    private UserRepository userRepository;


    public AuthenticatedUser() {
    }

    public String getUserId() {
        return this.userRepository.findByemail(SecurityContextHolder.getContext().getAuthentication().getName()).getId();
    }

}
