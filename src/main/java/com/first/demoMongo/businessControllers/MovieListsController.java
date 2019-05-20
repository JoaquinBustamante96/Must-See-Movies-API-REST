package com.first.demoMongo.businessControllers;

import com.first.demoMongo.documents.MovieLists;
import com.first.demoMongo.documents.User;
import com.first.demoMongo.exceptions.BadRequestException;
import com.first.demoMongo.repositories.MovieListsRepository;
import com.first.demoMongo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.Map;

@Controller
public class MovieListsController {

    @Autowired
    private MovieListsRepository movieListsRepository;
    @Autowired
    private UserController userController;

    public ArrayList<String> getList(String list, String authToken) throws BadRequestException {
        MovieLists movieLists = getIfListExists(list, userController.getUserId(authToken));
        return movieLists.getList(list);
    }

    public Map<String, Boolean> isMovieInUserLists(String id, String authToken) throws BadRequestException {
        MovieLists movieLists = this.movieListsRepository.findListsByUserId(this.userController.getUserId(authToken));
        return movieLists.isMovieInLists(id);
    }

    public void addMovieToList(String list, String id, String authToken) throws BadRequestException {
        MovieLists movieLists = this.getIfListExists(list, userController.getUserId(authToken));
        if (!movieLists.isMovieInList(list, id)) {
            movieLists.addMovieToList(list, id);
            this.movieListsRepository.save(movieLists);
        }
    }

    public void removeMovieFromList(String list, String id, String authToken) throws BadRequestException {
        MovieLists movieLists = this.getIfListExists(list, userController.getUserId(authToken));
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
