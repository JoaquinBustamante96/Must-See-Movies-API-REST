package com.first.demoMongo.businessControllers;

import com.first.demoMongo.documents.Role;
import com.first.demoMongo.documents.Token;
import com.first.demoMongo.documents.User;
import com.first.demoMongo.dtos.TokenOutputDto;
import com.first.demoMongo.dtos.UserDto;
import com.first.demoMongo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

    @Autowired
    UserRepository userRepository;

    public UserDto createUser(UserDto userDto, Role[] roles){

        User user = new User(userDto.getUsername(),userDto.getPassword(),userDto.getDni(),userDto.getAddress(),userDto.getEmail());
        userRepository.save(user);

        return new UserDto(user);
    }

    public TokenOutputDto login(String username){

        User user = userRepository.findByusername(username);
        user.setToken(new Token());
        userRepository.save(user);

        return new TokenOutputDto(user);
    }

}
