package com.first.demoMongo.dtos;

import java.time.LocalDate;
import java.util.Arrays;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class QueryMovieInputDto {

    private String artMovement = "";
    private String[] genre = {};
    private String country = "";
    private String region = "";
    private String language = "";
    private String color = "";
    private String sound = "";
    @Min(value = 0)
    @Max(value = 600)
    private int minRuntime = 0;
    @Min(value = 0)
    @Max(value = 600)
    private int maxRuntime = 600;
    @Min(value = 0)
    @Max(value = 2020)
    private int startYear = 0;
    @Min(value = 0)
    @Max(value = 2020)
    private int endYear = 2020;

    public QueryMovieInputDto() {
    }

    public QueryMovieInputDto(String artMovement, String[] genre, String country, String region,
                              String language, int minRuntime, int maxRuntime, String color,
                              String sound, int startYear, int endYear) {
        this.artMovement = artMovement;
        this.genre = genre;
        this.country = country;
        this.region = region;
        this.language = language;
        this.minRuntime = minRuntime;
        this.maxRuntime = maxRuntime;
        this.color = color;
        this.sound = sound;
        this.startYear = startYear;
        this.endYear = endYear;
    }

    public void setRegion(String region) {
        this.region = region;
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

    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }

    public void setEndYear(int endYear) {
        this.endYear = endYear;
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

    public String getArtMovement() {
        return artMovement;
    }

    public String getRegion() {
        return region;
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

    @Override
    public String toString() {
        return "QueryMovieInputDto{" +
                "artMovement='" + artMovement + '\'' +
                ", genre=" + Arrays.toString(genre) +
                ", country='" + country + '\'' +
                ", region='" + region + '\'' +
                ", language='" + language + '\'' +
                ", color='" + color + '\'' +
                ", sound='" + sound + '\'' +
                ", minRuntime=" + minRuntime +
                ", maxRuntime=" + maxRuntime +
                ", startYear=" + startYear +
                ", endYear=" + endYear +
                '}';
    }


    public LocalDate getStartYearAsLocalDate() {
        return LocalDate.of(startYear, 01, 01);
    }

    public LocalDate getEndYearAsLocalDate() {
        return LocalDate.of(endYear, 01, 01);
    }

}
