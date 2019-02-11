package com.first.demoMongo.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.first.demoMongo.documents.Movie;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Arrays;

@NotNull
public class MovieMinimumOutputDto {

    private String id;
    private String[] name;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate releaseDate;
    private String storyline;
    private String country;
    private String poster;
    private String[] director;


    public MovieMinimumOutputDto(){
        // Empty for framework
    }

    public MovieMinimumOutputDto(String id, String[] name, LocalDate releaseDate, String storyline, String country, String[] director) {
        this.id = id;
        this.name = name;
        this.releaseDate = releaseDate;
        this.storyline = storyline;
        this.country = country;
        this.director = director;
    }

    public MovieMinimumOutputDto(Movie movie){
        this.id=movie.getId();
        this.name=movie.getName();
        this.releaseDate=movie.getReleaseDate();
        this.storyline = movie.getStoryline();
        this.country = movie.getCountry();
        this.director = movie.getDirector();
        this.poster = movie.getPoster();
    }

    public String getStoryline() {
        return storyline;
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

    public String getCountry() {
        return country;
    }

    public String getPoster() {
        return poster;
    }

    public String[] getDirector() {
        return director;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public void setDirector(String[] director) {
        this.director = director;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setStoryline(String storyline) {
        this.storyline = storyline;
    }

    public void setName(String[] name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "MovieMinimumOutputDto{" +
                "id='" + id + '\'' +
                ", name=" + Arrays.toString(name) +
                ", releaseDate=" + releaseDate +
                ", storyline='" + storyline + '\'' +
                ", country='" + country + '\'' +
                ", poster='" + poster + '\'' +
                ", director=" + Arrays.toString(director) +
                '}';
    }
}
