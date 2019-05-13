package com.first.demoMongo.businessControllers;

import com.first.demoMongo.documents.Role;
import com.first.demoMongo.documents.Token;
import com.first.demoMongo.documents.User;
import com.first.demoMongo.dtos.TokenOutputDto;
import com.first.demoMongo.dtos.UserDto;
import com.first.demoMongo.exceptions.BadRequestException;
import com.first.demoMongo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    public UserDto createUser(UserDto userDto, Role[] roles) {

        User user = new User(userDto.getUsername(), userDto.getPassword(), userDto.getEmail(), roles);
        userRepository.save(user);

        return new UserDto(user);
    }

    public void resetPassword(String userName, String oldPassword, String newPassword, String token) throws BadRequestException {
        User user = this.userRepository.findByTokenValue(token);
        if (user != null && user.getUsername().equals(userName) &&
                new BCryptPasswordEncoder().matches(oldPassword, user.getPassword())) {
            user.setPassword(newPassword);
            this.userRepository.save(user);
        } else {
            throw new BadRequestException("Invalid Credentials");
        }
    }

    public TokenOutputDto login(String username) {

        User user = userRepository.findByusername(username);
        user.setToken(new Token());
        userRepository.save(user);

        return new TokenOutputDto(user);
    }

}
