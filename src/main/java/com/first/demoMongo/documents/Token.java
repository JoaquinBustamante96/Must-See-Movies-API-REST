package com.first.demoMongo.documents;

import com.first.demoMongo.businessServices.Encrypting;
import org.joda.time.DateTime;

import java.util.Date;

public class Token {

    private String value;

    private Date creationDate;

    public Token() {
        this.setValue(new Encrypting().encryptInBase64UrlSafe());
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
        this.creationDate = new Date();
    }


    public boolean isTokenExpired() {
        DateTime creationDate = new DateTime(this.getCreationDate());
        DateTime expirationDate = creationDate.plusDays(1);
        return expirationDate.isBeforeNow();
    }

    public Date getCreationDate() {
        return creationDate;
    }

    @Override
    public int hashCode() {
        return this.value.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        return value.equals(((Token) obj).value);
    }

    @Override
    public String toString() {
        return "Token [value=" + value + ", creationDate=" + creationDate.toString() + "]";
    }
}
