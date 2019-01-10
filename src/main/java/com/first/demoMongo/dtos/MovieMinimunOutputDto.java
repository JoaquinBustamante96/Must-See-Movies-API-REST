package com.first.demoMongo.dtos;

import com.first.demoMongo.documents.Movie;

import javax.validation.constraints.NotNull;

@NotNull
public class MovieMinimunOutputDto {

    private String id;
    private String[] name;


    public MovieMinimunOutputDto(){
        // Empty for framework
    }

    public MovieMinimunOutputDto(String id, String[] name, String releaseDate) {
        this.id = id;
        this.name = name;
    }

    public MovieMinimunOutputDto(Movie movie){
        this.id=movie.getId();
        this.name=movie.getName();
    }

    public String getId() {
        return id;
    }

    public String[] getName() {
        return name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String[] name) {
        this.name = name;
    }
}
