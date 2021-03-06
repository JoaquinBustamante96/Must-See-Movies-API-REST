package com.moviesmustsee.dataServices;

import com.moviesmustsee.documents.Movie;
import com.moviesmustsee.documents.PasswordResetToken;
import com.moviesmustsee.documents.Token;
import com.moviesmustsee.documents.User;

import java.util.ArrayList;
import java.util.List;

public class DatabaseGraph {

    private List<Movie> movieList;
    private List<User> userList;
    private List<PasswordResetToken> passwordResetTokenList;
    private List<Token> tokenList;

    public DatabaseGraph(){
        this.movieList = new ArrayList<>();
        this.userList = new ArrayList<>();
        this.passwordResetTokenList = new ArrayList<>();
        this.tokenList = new ArrayList<>();
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
    }
    public void setTokenList(List<Token> tokenList) {
        this.tokenList = tokenList;
    }
    public void setPasswordResetTokenList(List<PasswordResetToken> passwordResetTokenList) {
        this.passwordResetTokenList = passwordResetTokenList;
    }

    public List<Token> getTokenList() {
        return tokenList;
    }

    public List<PasswordResetToken> getPasswordResetTokenList() {
        return passwordResetTokenList;
    }

    public List<User> getUserList() {
        return userList;
    }
    public List<Movie> getMovieList() {
        return movieList;
    }
}
