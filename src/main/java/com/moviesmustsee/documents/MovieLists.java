package com.moviesmustsee.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Document
public class MovieLists {

    @Id
    private String id;
    @DBRef
    private User user;
    private Map<String, ArrayList<String>> lists;

    public MovieLists() {
    }

    public MovieLists(User user) {
        this.user = user;
        this.lists = new HashMap<>();
        lists.put("Watch later", new ArrayList<String>());
        lists.put("Watched", new ArrayList<String>());
    }

    public ArrayList<String> getList(String name) {
        return this.lists.get(name);
    }

    public void createList(String name) {
        lists.put(name, new ArrayList<>());
    }

    public void addMovieToList(String name, String id) {
        this.lists.get(name).add(id);
    }

    public boolean isMovieInList(String name, String id) {
        return this.lists.get(name).indexOf(id) != -1;
    }

    public void removeMovieFromList(String name, String id) {
        this.lists.get(name).remove(id);
    }

    public boolean containsList(String name) {
        return this.lists.containsKey(name);
    }

    public Map<String, Boolean> isMovieInLists(String id) {
        Map<String, Boolean> isInList = new HashMap<>();
        for (Map.Entry<String, ArrayList<String>> list : this.lists.entrySet()) {
            isInList.put(list.getKey(),this.isMovieInList(list.getKey(),id));
        }
        return isInList;
    }

    @Override
    public String toString() {
        return "MovieLists{" +
                "id='" + id + '\'' +
                ", user=" + user +
                ", lists=" + lists +
                '}';
    }
}


