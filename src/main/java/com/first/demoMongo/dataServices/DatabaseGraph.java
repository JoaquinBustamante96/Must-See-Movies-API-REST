package com.first.demoMongo.dataServices;

import com.first.demoMongo.documents.Movie;

import java.util.ArrayList;
import java.util.List;

public class DatabaseGraph {

    private List<Movie> movieList;

    public DatabaseGraph(){
        this.movieList = new ArrayList<>();
    }

    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
    }

    public List<Movie> getMovieList() {
        return movieList;
    }
}
