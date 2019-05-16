package com.first.demoMongo.businessControllers;

import com.first.demoMongo.documents.PasswordResetToken;
import com.first.demoMongo.documents.Role;
import com.first.demoMongo.documents.Token;
import com.first.demoMongo.documents.User;
import com.first.demoMongo.dtos.TokenOutputDto;
import com.first.demoMongo.dtos.UserDto;
import com.first.demoMongo.exceptions.BadRequestException;
import com.first.demoMongo.repositories.PasswordResetTokenRepository;
import com.first.demoMongo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    public UserDto createUser(UserDto userDto, Role[] roles) {

        User user = new User(userDto.getUsername(), userDto.getPassword(), userDto.getEmail(), roles);
        userRepository.save(user);

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

    public TokenOutputDto resetForgottenPassword(String email, String newPassword, String resetToken) throws BadRequestException {
        User user = this.userRepository.findByemail(email);
        if (user == null || !user.getEmail().equals(email)) {
            throw new BadRequestException("Invalid email or token");
        }
        PasswordResetToken passwordResetToken = passwordResetTokenRepository.findTokenByUserId(user.getId());

        if (passwordResetToken.getToken().getValue().equals(resetToken)) {
            user.setPassword(newPassword);
            return login(user.getUsername());
        } else {
            throw new BadRequestException("Invalid email or token");
        }
    }

    public TokenOutputDto login(String username) {

        User user = userRepository.findByusername(username);
        user.setToken(new Token());
        userRepository.save(user);

        return new TokenOutputDto(user);
    }

}
