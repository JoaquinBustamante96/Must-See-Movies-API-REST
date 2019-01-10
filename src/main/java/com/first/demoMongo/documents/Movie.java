package com.first.demoMongo.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Movie {

    @Id
    private String id;
    @Indexed
    private String[] name;
    @Indexed
    private String artMovement;
    @Indexed
    private String[] genre;
    private String storyline;
    @Indexed
    private String[] director;
    @Indexed
    private String country;
    private String lenguage;
    @Indexed
    private String releaseDate;//dd/mm/yyyy
    private int runtime;
    @Indexed
    private Boolean color;
    @Indexed
    private Boolean sound;
    private String trailer;
    private String poster;

    public Movie(){}

    public Movie(String[] name, String[] genre, String storyline, String artMovement,
                 String[] director, String country, String lenguage, String releaseDate,
                 int runtime, Boolean color, Boolean sound, String trailer, String poster) {
        this.name = name;
        this.genre = genre;
        this.artMovement=artMovement;
        this.storyline = storyline;
        this.director = director;
        this.country = country;
        this.lenguage = lenguage;
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

    public void setLenguage(String lenguage) {
        this.lenguage = lenguage;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
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

    public String getLenguage() {
        return lenguage;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public int getRuntime() {
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

    public int hashCode() {
        return this.id.hashCode();
    }

    public boolean equals(String id){
        if(this.id.equals(id)){
            return true;
        }
        else {
            return false;
        }
    }

}
