package com.first.demoMongo.restControllers;

import com.first.demoMongo.businessControllers.UserController;
import com.first.demoMongo.documents.Role;
import com.first.demoMongo.dtos.TokenOutputDto;
import com.first.demoMongo.dtos.UserDto;
import com.first.demoMongo.exceptions.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

@RestController
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping(UserResource.userMapping)
public class UserResource {

    public static final String userMapping = "/user";
    public static final String login = "/login";

    @Autowired
    private UserController userController;

    @PostMapping
    @PreAuthorize("permitAll()")
    public UserDto createUser(@RequestBody UserDto userDto) {
        return userController.createUser(userDto, new Role[]{Role.USER});
    }

    @PutMapping
    @PreAuthorize("hasRole('USER')")
    public void resetPassword(
            @RequestBody String userName
            , @RequestBody String oldPassword
            , @RequestBody String newPassword
            , @RequestBody String token) throws BadRequestException {
        this.userController.resetPassword(userName, oldPassword, newPassword, token);
    }

    @PreAuthorize("hasRole('AUTHENTICATED')")
    @PostMapping(UserResource.login)
    public TokenOutputDto login(@AuthenticationPrincipal User user) {
        return userController.login(user.getUsername());
    }

}
