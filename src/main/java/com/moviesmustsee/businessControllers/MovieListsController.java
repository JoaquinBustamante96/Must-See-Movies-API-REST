package com.moviesmustsee.businessControllers;

import com.moviesmustsee.businessServices.AuthenticatedUser;
import com.moviesmustsee.documents.MovieLists;
import com.moviesmustsee.exceptions.BadRequestException;
import com.moviesmustsee.repositories.MovieListsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.Map;

@Controller
public class MovieListsController {

    @Autowired
    private MovieListsRepository movieListsRepository;
    @Autowired
    private AuthenticatedUser authenticatedUser;

    public ArrayList<String> getList(String list) throws BadRequestException {
        MovieLists movieLists = getIfListExists(list, authenticatedUser.getUserId());
        return movieLists.getList(list);
    }

    public Map<String, Boolean> isMovieInUserLists(String id) throws BadRequestException {
        MovieLists movieLists = this.movieListsRepository.findListsByUserId(this.authenticatedUser.getUserId());
        return movieLists.isMovieInLists(id);
    }

    public void addMovieToList(String list, String id) throws BadRequestException {
        MovieLists movieLists = this.getIfListExists(list, authenticatedUser.getUserId());
        if (!movieLists.isMovieInList(list, id)) {
            movieLists.addMovieToList(list, id);
            this.movieListsRepository.save(movieLists);
        }
    }

    public void removeMovieFromList(String list, String id) throws BadRequestException {
        MovieLists movieLists = this.getIfListExists(list, authenticatedUser.getUserId());
        movieLists.removeMovieFromList(list, id);
        this.movieListsRepository.save(movieLists);
    }

    private MovieLists getIfListExists(String list, String userId) throws BadRequestException {
        MovieLists movieLists = this.movieListsRepository.findListsByUserId(userId);
        if (!movieLists.containsList(list)) {
            throw new BadRequestException("Non-existent list");
        }
        return movieLists;
    }

}
