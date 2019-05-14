package com.first.demoMongo.documents;

import org.joda.time.DateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
public class PasswordResetToken {

    @Id
    private String id;

    private Token token;

    @DBRef
    private User user;

    public PasswordResetToken(User user) {
        this.user = user;
        this.token = new Token();
    }

    public String getId() {
        return id;
    }

    public Token getToken() {
        return token;
    }

    public User getUser() {
        return user;
    }

    public boolean isExpired() {
        DateTime creationDate = new DateTime(this.token.getCreationDate());
        DateTime expirationDate = creationDate.plusDays(1);
        return expirationDate.isBeforeNow();
    }
}