package com.moviesmustsee.dtos;

import javax.validation.constraints.NotEmpty;

public class PosterDto {

    @NotEmpty
    String id;

    public PosterDto(String ID){
        this.id = ID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
