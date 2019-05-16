package com.first.demoMongo.businessControllers;

import com.first.demoMongo.documents.MovieLists;
import com.first.demoMongo.documents.User;
import com.first.demoMongo.exceptions.BadRequestException;
import com.first.demoMongo.repositories.MovieListsRepository;
import com.first.demoMongo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;

@Controller
public class MovieListsController {

    @Autowired
    private MovieListsRepository movieListsRepository;
    @Autowired
    private UserRepository userRepository;

    public ArrayList<String> getList(String name, String authToken) throws BadRequestException{
       MovieLists movieLists = getIfListExists(name,this.getUserId(authToken));
       return movieLists.getList(name);
    }

    public void addMovieToList(String name, String id, String authToken) throws BadRequestException {
        MovieLists movieLists = this.getIfListExists(name, getUserId(authToken));
        movieLists.addMovieToList(name, id);
        this.movieListsRepository.save(movieLists);
    }

    public void removeMovieFromList(String name, String id, String authToken) throws BadRequestException {
        MovieLists movieLists = this.getIfListExists(name, getUserId(authToken));
        movieLists.removeMovieFromList(name, id);
        this.movieListsRepository.save(movieLists);
    }

    private MovieLists getIfListExists(String name, String userId) throws BadRequestException {
        MovieLists movieLists = this.movieListsRepository.findListsByUserId(this.getUserId(userId));
        if (!movieLists.containsList(name)) {
            throw new BadRequestException("Non-existent list");
        }
        return movieLists;
    }

    private String getUserId(String authToken) throws BadRequestException {
        User user = this.userRepository.findByTokenValue(authToken);
        if (user == null) {
            throw new BadRequestException("Invalid Token");
        }
        return user.getId();
    }


}
