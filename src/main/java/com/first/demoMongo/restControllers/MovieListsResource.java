package com.first.demoMongo.restControllers;

import com.first.demoMongo.businessControllers.MovieListsController;
import com.first.demoMongo.exceptions.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(MovieResorce.MOVIE + MovieListsResource.LIST)
@PreAuthorize("hasRole('AUTHENTICATED')")
public class MovieListsResource {

    public static final String LIST = "/list";

    @Autowired
    private MovieListsController movieListsController;

    @GetMapping()
    @PreAuthorize("hasRole('USER')")
    public ArrayList<String> getList(@RequestParam String name, @RequestParam String authToken) throws BadRequestException{
        return this.movieListsController.getList(name, authToken);
    }

    @PutMapping()
    @PreAuthorize("hasRole('USER')")
    public void addMovieToList(@RequestParam String name, @RequestParam String id, @RequestParam String authToken) throws BadRequestException {
        this.movieListsController.addMovieToList(name, id, authToken);
    }

    @DeleteMapping()
    @PreAuthorize("hasRole('USER')")
    public void removeMovieFromList(@RequestParam String name, @RequestParam String id, @RequestParam String authToken) throws BadRequestException {
        this.movieListsController.removeMovieFromList(name, id, authToken);
    }
}
