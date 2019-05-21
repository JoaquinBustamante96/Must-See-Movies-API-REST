package com.first.demoMongo.restControllers;

import com.first.demoMongo.businessControllers.MovieListsController;
import com.first.demoMongo.exceptions.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;

@RestController
@RequestMapping(MovieListsResource.LIST)
@PreAuthorize("hasRole('AUTHENTICATED')")
public class MovieListsResource {

    public static final String LIST = "/list";

    @Autowired
    private MovieListsController movieListsController;

    @GetMapping()
    @PreAuthorize("hasRole('USER')")
    public ArrayList<String> getList(@RequestParam String name) throws BadRequestException {
        return this.movieListsController.getList(name);
    }

    @GetMapping(MovieResorce.MOVIE)
    @PreAuthorize("hasRole('USER')")
    public Map<String, Boolean> isMovieInUserLists(@RequestParam String id) throws BadRequestException {
        return this.movieListsController.isMovieInUserLists(id);
    }

    @PutMapping(MovieResorce.MOVIE)
    @PreAuthorize("hasRole('USER')")
    public void addMovieToList(@RequestParam String list, @RequestParam String id) throws BadRequestException {
        this.movieListsController.addMovieToList(list, id);
    }

    @DeleteMapping(MovieResorce.MOVIE)
    @PreAuthorize("hasRole('USER')")
    public void removeMovieFromList(@RequestParam String list, @RequestParam String id) throws BadRequestException {
        this.movieListsController.removeMovieFromList(list, id);
    }
}
