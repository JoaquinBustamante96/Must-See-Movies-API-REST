package com.first.demoMongo.dataServices;

import com.first.demoMongo.documents.Movie;
import com.first.demoMongo.documents.User;

import java.util.ArrayList;
import java.util.List;

public class DatabaseGraph {

    private List<Movie> movieList;
    private List<User> userList;

    public DatabaseGraph(){
        this.movieList = new ArrayList<>();
        this.userList = new ArrayList<>();
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
    }
    public List<User> getUserList() {
        return userList;
    }
    public List<Movie> getMovieList() {
        return movieList;
    }
}
