package com.moviesmustsee.documents;

public class MovieLinks {

    private String youtubeId;
    private String imdb;

    public MovieLinks() {
    }

    public MovieLinks(String youtubeId,String imdb){
        this.youtubeId = youtubeId;
        this.imdb = imdb;
    }

    public String getYoutubeId() {
        return youtubeId;
    }

    public String getImdb() {
        return imdb;
    }

    public void setYoutubeId(String youtubeId) {
        this.youtubeId = youtubeId;
    }

    public void setImdb(String imdb) {
        this.imdb = imdb;
    }
}
