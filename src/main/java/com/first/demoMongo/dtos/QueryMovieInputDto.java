package com.first.demoMongo.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Arrays;

public class QueryMovieInputDto {

    private String artMovement;
    private String[] genre;
    private String country;
    private String language;
    private String color;
    private String sound;
    private int minRuntime;
    private int maxRuntime;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate startDate;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate endDate;

    public QueryMovieInputDto() {
    }

    public QueryMovieInputDto( String artMovement, String[] genre, String country,
                              String language, int minRuntime, int maxRuntime, String color,
                              String sound, LocalDate startDate, LocalDate endDate) {
        this.artMovement = artMovement;
        this.genre = genre;
        this.country = country;
        this.language = language;
        this.minRuntime = minRuntime;
        this.maxRuntime = maxRuntime;
        this.color = color;
        this.sound = sound;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public void setArtMovement(String artMovement) {
        this.artMovement = artMovement;
    }

    public void setGenre(String[] genre) {
        this.genre = genre;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setMinRuntime(int minRuntime) {
        this.minRuntime = minRuntime;
    }

    public void setMaxRuntime(int maxRuntime) {
        this.maxRuntime = maxRuntime;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }


    public String getArtMovement() {
        return artMovement;
    }

    public String[] getGenre() {
        return genre;
    }

    public String getCountry() {
        return country;
    }

    public String getLanguage() {
        return language;
    }

    public int getMinRuntime() {
        return minRuntime;
    }

    public int getMaxRuntime() {
        return maxRuntime;
    }

    public String getColor() {
        return color;
    }

    public String getSound() {
        return sound;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    @Override
    public String toString() {
        return "QueryMovieInputDto{" +
                "artMovement='" + artMovement + '\'' +
                ", genre=" + Arrays.toString(genre) +
                ", country='" + country + '\'' +
                ", language='" + language + '\'' +
                ", color='" + color + '\'' +
                ", sound='" + sound + '\'' +
                ", minRuntime=" + minRuntime +
                ", maxRuntime=" + maxRuntime +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
