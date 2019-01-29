package com.first.demoMongo.restControllers;

import com.first.demoMongo.businessControllers.UserController;
import com.first.demoMongo.documents.Role;
import com.first.demoMongo.dtos.TokenOutputDto;
import com.first.demoMongo.dtos.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(UserResource.userMapping)
public class UserResource {

    public static final String userMapping = "/user";
    public static final String login = "/login";

    @Autowired
    UserController userController;

    @PostMapping
    public UserDto createUser(@RequestBody UserDto userDto){

        return userController.createUser(userDto,new Role[]{Role.USER});
    }

    @PreAuthorize("hasRole('AUTHENTICATED')")
    @PostMapping(UserResource.login)
    public TokenOutputDto login(@AuthenticationPrincipal User user){

        return userController.login(user.getUsername());
    }

}
