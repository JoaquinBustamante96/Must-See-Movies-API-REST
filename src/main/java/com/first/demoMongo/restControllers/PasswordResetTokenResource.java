package com.first.demoMongo.restControllers;


import com.first.demoMongo.businessControllers.PasswordResetTokenController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(PasswordResetTokenResource.PASSWORDRESET_TOKEN)
@PreAuthorize("permitAll()")
public class PasswordResetTokenResource {

    public static final String PASSWORDRESET_TOKEN = "/reset";

    @Autowired
    PasswordResetTokenController passwordResetTokenController;

    @PostMapping()
    @PreAuthorize("permitAll()")
    public void forgotPassword(String email){
        this.passwordResetTokenController.processForgotPassword(email);
    }

}
