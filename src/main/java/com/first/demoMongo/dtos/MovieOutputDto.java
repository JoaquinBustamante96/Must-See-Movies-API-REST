package com.first.demoMongo.dtos;

import com.first.demoMongo.documents.Movie;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Arrays;

@NotNull
@NotEmpty
public class MovieOutputDto extends MovieMinimumOutputDto {

    private String[] genre;
    private String artMovement;
    private String language;
    private int runtime;
    private String color;
    private String sound;
    private String trailer;

    public MovieOutputDto(){
        // Empty for framework
    }

    public MovieOutputDto(String id, String[] name, LocalDate releaseDate,
                          String artMovement, String[] genre, String storyline,
                          String[] director, String country, String language,
                          int durationInMin, String color, String sound,String trailer) {
        super(id, name, releaseDate,storyline,country,director);
        this.genre = genre;
        this.artMovement=artMovement;
        this.language = language;
        this.runtime = durationInMin;
        this.color = color;
        this.sound = sound;
        this.trailer = trailer;
    }

    public MovieOutputDto(Movie movie){
        this(movie.getId(),movie.getName(),movie.getReleaseDate(),movie.getArtMovement(),movie.getGenre(),
                movie.getStoryline(),movie.getDirector(),movie.getCountry(),movie.getLanguage(),
                movie.getRuntime(),movie.getColor(),movie.getSound(),movie.getTrailer());
    }

    public void setGenre(String[] genre) {
        this.genre = genre;
    }

    public void setArtMovement(String artMovement) {
        this.artMovement = artMovement;
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

    public String[] getGenre() {
        return genre;
    }

    public String getArtMovement() {
        return artMovement;
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

    @Override
    public String toString() {
        return "MovieOutputDto{" +
                "genre=" + Arrays.toString(genre) +
                ", artMovement='" + artMovement + '\'' +
                ", language='" + language + '\'' +
                ", runtime=" + runtime +
                ", color='" + color + '\'' +
                ", sound='" + sound + '\'' +
                ", trailer='" + trailer + '\'' +
                '}';
    }
}