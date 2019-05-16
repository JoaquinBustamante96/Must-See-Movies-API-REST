package com.first.demoMongo.businessServices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
public class MailClient {

    private JavaMailSender mailSender;

    @Autowired
    private MailContentBuilder mailContentBuilder;

    @Value("${spring.mail.username}@gmail.com")
    private String from;

    @Autowired
    public MailClient(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void prepareAndSend(String recipient, String message,String subject) {
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom(this.from);
            messageHelper.setTo(recipient);
            messageHelper.setSubject(subject);
            messageHelper.setText(message);
            String content = mailContentBuilder.build(message);
            messageHelper.setText(content, true);
        };
        try {
            mailSender.send(messagePreparator);
        } catch (MailException e) {
            throw new com.first.demoMongo.exceptions.MailException("error sending email");
        }
    }

}