package com.first.demoMongo.dtos;

public class MovieLinksDto {

    private String youtubeId;
    private String imdb;

    public MovieLinksDto(String youtubeId, String imdb) {
        this.youtubeId = youtubeId;
        this.imdb = imdb;
    }

    public void setYoutubeId(String youtubeId) {
        this.youtubeId = youtubeId;
    }

    public void setImdb(String imdb) {
        this.imdb = imdb;
    }

    public String getYoutubeId() {
        return youtubeId;
    }

    public String getImdb() {
        return imdb;
    }
}
