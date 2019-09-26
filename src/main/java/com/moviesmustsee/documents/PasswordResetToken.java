package com.moviesmustsee.documents;

import org.joda.time.DateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class PasswordResetToken {

    @Id
    private String id;

    private Token token;

    @DBRef
    private User user;

    public PasswordResetToken(){}

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

    public void setId(String id) {
        this.id = id;
    }

    public void generateNewToken(){
        this.token = new Token();
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isExpired() {
        DateTime creationDate = new DateTime(this.token.getCreationDate());
        DateTime expirationDate = creationDate.plusDays(1);
        return expirationDate.isBeforeNow();
    }

    @Override
    public String toString() {
        return "PasswordResetToken{" +
                "id='" + id + '\'' +
                ", token=" + token +
                ", user=" + user +
                '}';
    }
}