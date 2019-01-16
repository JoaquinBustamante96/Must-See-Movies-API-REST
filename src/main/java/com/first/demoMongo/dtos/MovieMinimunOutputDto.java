package com.first.demoMongo.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.first.demoMongo.documents.Movie;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@NotNull
public class MovieMinimunOutputDto {

    private String id;
    private String[] name;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate releaseDate;


    public MovieMinimunOutputDto(){
        // Empty for framework
    }

    public MovieMinimunOutputDto(String id, String[] name, LocalDate releaseDate) {
        this.id = id;
        this.name = name;
    }

    public MovieMinimunOutputDto(Movie movie){
        this.id=movie.getId();
        this.name=movie.getName();
        this.releaseDate=movie.getReleaseDate();
    }

    public String getId() {
        return id;
    }

    public String[] getName() {
        return name;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String[] name) {
        this.name = name;
    }
}
