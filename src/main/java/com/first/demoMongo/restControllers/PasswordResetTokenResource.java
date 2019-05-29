package com.first.demoMongo.restControllers;


import com.first.demoMongo.businessControllers.PasswordResetTokenController;
import com.first.demoMongo.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(PasswordResetTokenResource.PASSWORD_RESET_TOKEN)
@PreAuthorize("permitAll()")
public class PasswordResetTokenResource {

    public static final String PASSWORD_RESET_TOKEN = "/forgot-password";

    @Autowired
    private PasswordResetTokenController passwordResetTokenController;

    @PostMapping()
    @PreAuthorize("permitAll()")
    public void forgotPassword(@RequestParam String email) throws NotFoundException {
        this.passwordResetTokenController.processForgotPassword(email);
    }

}
