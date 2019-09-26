package com.moviesmustsee.dtos;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class ResetForgottenPasswordInputDto {

    @NotEmpty @NotNull
    String password;
    @NotEmpty @NotNull
    String resetToken;

    public ResetForgottenPasswordInputDto(){
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setResetToken(String resetToken) {
        this.resetToken = resetToken;
    }

    public String getPassword() {
        return password;
    }

    public String getResetToken() {
        return resetToken;
    }
}
