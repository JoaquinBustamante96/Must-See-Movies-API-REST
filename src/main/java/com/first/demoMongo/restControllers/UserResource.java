package com.first.demoMongo.restControllers;

import com.first.demoMongo.businessControllers.UserController;
import com.first.demoMongo.documents.Role;
import com.first.demoMongo.dtos.ResetForgottenPasswordInputDto;
import com.first.demoMongo.dtos.TokenOutputDto;
import com.first.demoMongo.dtos.UserDto;
import com.first.demoMongo.exceptions.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

@RestController
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping(UserResource.userMapping)
public class UserResource {

    public static final String userMapping = "/user";
    public static final String login = "/login";
    public static final String reset = "/reset";
    public static final String password = "/password";

    @Autowired
    private UserController userController;

    @PostMapping()
    @PreAuthorize("permitAll()")
    public UserDto createUser(@RequestBody UserDto userDto) throws BadRequestException{
        return userController.createUser(userDto, new Role[]{Role.USER});
    }

    @PutMapping()
    @PreAuthorize("hasRole('USER')")
    public void resetPassword(
            String email
            , String oldPassword
            , String newPassword
            , String token) throws BadRequestException {
        this.userController.resetPassword(email, oldPassword, newPassword, token);
    }

    @PutMapping(UserResource.reset+UserResource.password)
    @PreAuthorize("permitAll()")
    public TokenOutputDto resetForgottenPassword(
            @RequestBody ResetForgottenPasswordInputDto resetForgottenPasswordInputDto) throws BadRequestException{
       return this.userController
               .resetForgottenPassword(
                       resetForgottenPasswordInputDto.getPassword(),
                       resetForgottenPasswordInputDto.getResetToken());
    }

    @PreAuthorize("hasRole('AUTHENTICATED')")
    @PostMapping(UserResource.login)
    public TokenOutputDto login(@AuthenticationPrincipal User user) {
        return userController.login(user.getUsername());
    }

}
