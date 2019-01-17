package com.first.demoMongo.dtos;

import java.time.LocalDate;

public class queryMovieInputDto {

   private String name;
   private String artMovement;
   private String genre;
   private String country;
   private String lenguage;
   private int minRuntime;
   private int maxRuntime;
   private boolean color;
   private boolean sound;
   private LocalDate startDate;
   private LocalDate endDate;

    public queryMovieInputDto(){
    }

    public queryMovieInputDto(String name, String artMovement, String genre, String country,
                              String lenguage, int minRuntime, int maxRuntime, boolean color,
                              boolean sound, LocalDate startDate, LocalDate endDate) {
        this.name = name;
        this.artMovement = artMovement;
        this.genre = genre;
        this.country = country;
        this.lenguage = lenguage;
        this.minRuntime = minRuntime;
        this.maxRuntime = maxRuntime;
        this.color = color;
        this.sound = sound;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setArtMovement(String artMovement) {
        this.artMovement = artMovement;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setLenguage(String lenguage) {
        this.lenguage = lenguage;
    }

    public void setMinRuntime(int minRuntime) {
        this.minRuntime = minRuntime;
    }

    public void setMaxRuntime(int maxRuntime) {
        this.maxRuntime = maxRuntime;
    }

    public void setColor(boolean color) {
        this.color = color;
    }

    public void setSound(boolean sound) {
        this.sound = sound;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getName() {
        return name;
    }

    public String getArtMovement() {
        return artMovement;
    }

    public String getGenre() {
        return genre;
    }

    public String getCountry() {
        return country;
    }

    public String getLenguage() {
        return lenguage;
    }

    public int getMinRuntime() {
        return minRuntime;
    }

    public int getMaxRuntime() {
        return maxRuntime;
    }

    public boolean isColor() {
        return color;
    }

    public boolean isSound() {
        return sound;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }
}
