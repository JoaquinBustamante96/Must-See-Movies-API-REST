package com.first.demoMongo.documents;

public enum Role {
    ADMIN, USER, AUTHENTICATED;

    public String roleName() {
        return "ROLE_" + this.toString();
    }

}
