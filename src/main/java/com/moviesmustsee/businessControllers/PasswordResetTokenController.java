package com.moviesmustsee.businessControllers;


import com.moviesmustsee.businessServices.MailClient;
import com.moviesmustsee.documents.PasswordResetToken;
import com.moviesmustsee.documents.User;
import com.moviesmustsee.exceptions.NotFoundException;
import com.moviesmustsee.repositories.PasswordResetTokenRepository;
import com.moviesmustsee.repositories.UserRepository;
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
