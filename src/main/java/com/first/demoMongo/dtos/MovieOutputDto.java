package com.first.demoMongo.dtos;

import com.first.demoMongo.documents.Movie;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@NotNull
@NotEmpty
public class MovieOutputDto extends MovieMinimunOutputDto {

    private String genre;
    private String storyline;
    private String[] artMovement;
    private String[] director;
    private String country;
    private String lenguage;
    private int runtime;
    private Boolean color;
    private Boolean sound;
    private String trailer;
    private String poster;

    public MovieOutputDto(){
        // Empty for framework
    }

    public MovieOutputDto(String id, String[] name, String releaseDate, String[] artMovement, String genre, String storyline, String[] director, String country, String lenguage, int durationInMin, Boolean color, Boolean sound) {
        super(id, name, releaseDate);
        this.genre = genre;
        this.artMovement=artMovement;
        this.storyline = storyline;
        this.director = director;
        this.country = country;
        this.lenguage = lenguage;
        this.runtime = durationInMin;
        this.color = color;
        this.sound = sound;
    }
    public MovieOutputDto(Movie movie){
        this(movie.getId(),movie.getName(),movie.getReleaseDate(),movie.getGenre(),movie.getArtMovement(),
                movie.getStoryline(),movie.getDirector(),movie.getCountry(),movie.getLenguage(),
                movie.getRuntime(),movie.getColor(),movie.getSound());
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setStoryline(String storyline) {
        this.storyline = storyline;
    }

    public void setDirector(String[] director) {
        this.director = director;
    }

    public void setArtMovement(String[] artMovement) {
        this.artMovement = artMovement;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setLenguage(String lenguage) {
        this.lenguage = lenguage;
    }

    public void setDurationInMin(int durationInMin) {
        this.runtime = durationInMin;
    }

    public void setColor(Boolean color) {
        this.color = color;
    }

    public void setSound(Boolean sound) {
        this.sound = sound;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getGenre() {
        return genre;
    }

    public String[] getArtMovement() {
        return artMovement;
    }
    public String getStoryline() {
        return storyline;
    }

    public String[] getDirector() {
        return director;
    }

    public String getCountry() {
        return country;
    }

    public String getLenguage() {
        return lenguage;
    }

    public int getDurationInMin() {
        return runtime;
    }

    public Boolean getColor() {
        return color;
    }

    public Boolean getSound() {
        return sound;
    }

    public String getTrailer() {
        return trailer;
    }

    public String getPoster() {
        return poster;
    }
}