package com.first.demoMongo.dtos;

import com.first.demoMongo.documents.Movie;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Arrays;

@NotNull
@NotEmpty
public class MovieOutputDto extends MovieMinimunOutputDto {

    private String[] genre;
    private String storyline;
    private String artMovement;
    private String[] director;
    private String country;
    private String language;
    private int runtime;
    private String color;
    private String sound;
    private String trailer;
    private String poster;

    public MovieOutputDto(){
        // Empty for framework
    }

    public MovieOutputDto(String id, String[] name, LocalDate releaseDate, String artMovement, String[] genre, String storyline, String[] director, String country, String language, int durationInMin, String color, String sound) {
        super(id, name, releaseDate);
        this.genre = genre;
        this.artMovement=artMovement;
        this.storyline = storyline;
        this.director = director;
        this.country = country;
        this.language = language;
        this.runtime = durationInMin;
        this.color = color;
        this.sound = sound;
    }

    public MovieOutputDto(Movie movie){
        this(movie.getId(),movie.getName(),movie.getReleaseDate(),movie.getArtMovement(),movie.getGenre(),
                movie.getStoryline(),movie.getDirector(),movie.getCountry(),movie.getLanguage(),
                movie.getRuntime(),movie.getColor(),movie.getSound());
    }

    public void setGenre(String[] genre) {
        this.genre = genre;
    }

    public void setStoryline(String storyline) {
        this.storyline = storyline;
    }

    public void setDirector(String[] director) {
        this.director = director;
    }

    public void setArtMovement(String artMovement) {
        this.artMovement = artMovement;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setDurationInMin(int durationInMin) {
        this.runtime = durationInMin;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String[] getGenre() {
        return genre;
    }

    public String getArtMovement() {
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

    public String getLanguage() {
        return language;
    }

    public int getDurationInMin() {
        return runtime;
    }

    public String getColor() {
        return color;
    }

    public String getSound() {
        return sound;
    }

    public String getTrailer() {
        return trailer;
    }

    public String getPoster() {
        return poster;
    }

    @Override
    public String toString() {
        return "MovieOutputDto{" +
                "genre=" + Arrays.toString(genre) +
                ", storyline='" + storyline + '\'' +
                ", artMovement='" + artMovement + '\'' +
                ", director=" + Arrays.toString(director) +
                ", country='" + country + '\'' +
                ", language='" + language + '\'' +
                ", runtime=" + runtime +
                ", color='" + color + '\'' +
                ", sound='" + sound + '\'' +
                ", trailer='" + trailer + '\'' +
                ", poster='" + poster + '\'' +
                '}';
    }
}