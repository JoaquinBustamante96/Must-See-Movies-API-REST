package com.first.demoMongo.dtos;

import com.first.demoMongo.documents.Role;
import com.first.demoMongo.documents.User;

import javax.validation.constraints.NotEmpty;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

public class UserDto {
    @NotEmpty
    private String username;
    @NotEmpty
    private String password;
    @NotEmpty
    private String email;
    @NotEmpty
    private String dni;
    @NotEmpty
    private String address;
    @NotEmpty
    private Boolean active;

    private Role[] roles;

    private Date registrationDate;

    public UserDto() {
        // Empty for framework
    }

    // INPUT
    public UserDto(String username, String password, String email, String dni, String address, Boolean active) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.dni = dni;
        this.address = address;
        this.active = active;
    }

    // OUTPUT
    public UserDto(User user) {
        this(user.getUsername(),user.getPassword(),user.getEmail(),user.getDni(),user.getAddress(),user.isActive());
        this.registrationDate = user.getRegistrationDate();
        this.setRoles(user.getRoles());

    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public void setRoles(Role[] roles) {
        this.roles = roles;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getDni() {
        return dni;
    }

    public String getAddress() {
        return address;
    }

    public Boolean getActive() {
        return active;
    }

    public Role[] getRoles() {
        return roles;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }
}
