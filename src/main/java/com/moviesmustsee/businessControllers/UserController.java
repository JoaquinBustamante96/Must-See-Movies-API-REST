package com.moviesmustsee.businessControllers;

import com.moviesmustsee.documents.*;
import com.moviesmustsee.dtos.TokenOutputDto;
import com.moviesmustsee.dtos.UserDto;
import com.moviesmustsee.exceptions.BadRequestException;
import com.moviesmustsee.repositories.MovieListsRepository;
import com.moviesmustsee.repositories.PasswordResetTokenRepository;
import com.moviesmustsee.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MovieListsRepository movieListsRepository;

    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    public UserDto createUser(UserDto userDto, Role[] roles) throws BadRequestException {

        if (userRepository.findByemail(userDto.getEmail()) != null) {
            throw new BadRequestException("email already in use");
        }

        User user = new User(userDto.getEmail(), userDto.getPassword(), roles);
        userRepository.save(user);
        this.movieListsRepository.save(new MovieLists(user));

        return new UserDto(user);
    }

    public void resetPassword(String email, String oldPassword, String newPassword, String token) throws BadRequestException {
        User user = this.userRepository.findByTokenValue(token);
        if (user != null && user.getEmail().equals(email) &&
                new BCryptPasswordEncoder().matches(oldPassword, user.getPassword())) {
            user.setPassword(newPassword);
            this.userRepository.save(user);
        } else {
            throw new BadRequestException("Invalid Credentials");
        }
    }

    public TokenOutputDto resetForgottenPassword(String password, String resetToken) throws BadRequestException {
        PasswordResetToken passwordResetToken = passwordResetTokenRepository.findTokenByValue(resetToken);
        if (passwordResetToken == null) {
            throw new BadRequestException("invalid token");
        }
        User user = passwordResetToken.getUser();
        user.setPassword(password);
        this.userRepository.save(user);
        return login(user.getUsername());
    }

    public TokenOutputDto login(String username) {

        User user = userRepository.findByemail(username);
        user.setToken(new Token());
        userRepository.save(user);

        return new TokenOutputDto(user);
    }

    public String getUserIdByEmail(String email) throws BadRequestException {
        User user = this.userRepository.findByemail(email);
        if (user == null) {
            throw new BadRequestException("Invalid email");
        }
        return user.getId();
    }

}
