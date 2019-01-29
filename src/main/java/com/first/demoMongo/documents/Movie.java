package com.first.demoMongo.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Document
public class Movie {

    @Id
    private String id;
    @Indexed
    private String[] name;
    private String artMovement;
    private String[] genre;
    private String storyline;
    private String[] director;
    private String country;
    private String language;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate releaseDate;
    private int runtime;
    private String color;
    private String sound;
    private String trailer;
    private String poster;

    public Movie() {
    }

    public Movie(String[] name, String[] genre, String storyline, String artMovement,
                 String[] director, String country, String language, LocalDate releaseDate,
                 int runtime, String color, String sound, String trailer, String poster) {
        this.name = name;
        this.genre = genre;
        this.artMovement = artMovement;
        this.storyline = storyline;
        this.director = director;
        this.country = country;
        this.language = language;
        this.releaseDate = releaseDate;
        this.runtime = runtime;
        this.color = color;
        this.sound = sound;
        this.trailer = trailer;
        this.poster = poster;
    }

    public void setArtMovement(String artMovement) {
        this.artMovement = artMovement;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String[] name) {
        this.name = name;
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

    public void setCountry(String country) {
        this.country = country;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
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

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getArtMovement() {
        return artMovement;
    }

    public String[] getName() {
        return name;
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

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public int getRuntime() {
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

    public int hashCode() {
        return this.id.hashCode();
    }

    public boolean equals(String id) {
        if (this.id.equals(id)) {
            return true;
        } else {
            return false;
        }
    }

}
