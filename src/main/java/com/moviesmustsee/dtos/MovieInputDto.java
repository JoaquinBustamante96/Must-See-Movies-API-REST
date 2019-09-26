package com.moviesmustsee.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Arrays;

public class MovieInputDto {

    @NotNull @NotEmpty
    private String[] name;
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate releaseDate;
    private String artMovement = "";
    @NotNull @NotEmpty
    private  String[] genre;
    @NotNull @NotEmpty
    private String storyline;
    @NotNull @NotEmpty
    private String[] director;
    @NotNull @NotEmpty
    private String country;
    @NotNull @NotEmpty
    private String language;
    @Min(value = 0)
    @Max(value = 600)
    private int runtime;
    @NotNull @NotEmpty
    private String color;
    @NotNull @NotEmpty
    private String sound;
    private MovieLinksDto movieLinks;
    private String poster;

    public MovieInputDto(){
        //empty for framework
    }

    public MovieInputDto(String[] name, LocalDate releaseDate, String artMovement, String[] genre, String storyline, String[] director, String country, String language,
                         int runtime, String color, String sound, String poster,MovieLinksDto movieLinks) {
        this.name=name;
        this.releaseDate=releaseDate;
        this.genre = genre;
        this.artMovement=artMovement;
        this.storyline = storyline;
        this.director = director;
        this.country = country;
        this.language = language;
        this.runtime = runtime;
        this.color = color;
        this.sound = sound;
        this.poster=poster;
        this.movieLinks = movieLinks;
    }

    public void setMovieLinks(MovieLinksDto movieLinks) {
        this.movieLinks = movieLinks;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public void setArtMovement(String artMovement) {
        this.artMovement = artMovement;
    }

    public void setGenre(String[] genre) {
        this.genre = genre;
    }

    public void setName(String[] name) {
        this.name = name;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setStoryline(String storyline) {
        this.storyline = storyline;
    }

    public void setDirector(String[] director) {
        this.director = director;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }

    public String[] getName() {
        return name;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public String[] getGenre() {
        return genre;
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

    public int getRuntime() {
        return runtime;
    }

    public String getColor() {
        return color;
    }

    public MovieLinksDto getMovieLinks() {
        return movieLinks;
    }

    public String getSound() {
        return sound;
    }


    public String getArtMovement() {
        return artMovement;
    }

    public String getPoster() {
        return poster;
    }

    @Override
    public String toString() {
        return "MovieInputDto{" +
                "name='" + Arrays.toString(name)+ '\'' +
                ", releaseDate='" + releaseDate.toString() + '\'' +
                ", artMovement='" + artMovement + '\'' +
                ", genre=" + Arrays.toString(genre) +
                ", storyline='" + storyline + '\'' +
                ", director=" + Arrays.toString(director) +
                ", country=" + country +
                ", language='" + language + '\'' +
                ", runtime=" + runtime +
                ", color=" + color +
                ", sound=" + sound +
                '}';
    }
}
