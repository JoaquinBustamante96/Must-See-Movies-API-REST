package com.first.demoMongo.businessControllers;


import com.first.demoMongo.businessServices.MailClient;
import com.first.demoMongo.documents.PasswordResetToken;
import com.first.demoMongo.documents.User;
import com.first.demoMongo.exceptions.NotFoundException;
import com.first.demoMongo.repositories.PasswordResetTokenRepository;
import com.first.demoMongo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class PasswordResetTokenController {

    @Autowired
    PasswordResetTokenRepository passwordResetTokenRepository;

    @Autowired
    MailClient mailClient;

    @Autowired
    UserRepository userRepository;

    public void processForgotPassword(String email) throws NotFoundException {
        User user = this.userRepository.findByemail(email);
        if (user == null) {
            throw new NotFoundException("no user found with the given email");
        }
        PasswordResetToken passwordResetToken = passwordResetTokenRepository.findTokenByUserId(user.getId());
        if (passwordResetToken != null) {
            passwordResetToken.generateNewToken();
        } else {
            passwordResetToken = new PasswordResetToken(user);
        }

        this.mailClient.prepareAndSend(email, passwordResetToken.getToken().getValue(), "Forgotten password reset");
    }

}
