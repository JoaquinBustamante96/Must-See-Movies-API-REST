package com.first.demoMongo.restControllers;

import com.first.demoMongo.businessControllers.MoviesController;
import com.first.demoMongo.documents.Movie;
import com.first.demoMongo.dtos.MovieInputDto;
import com.first.demoMongo.dtos.MovieMinimumOutputDto;
import com.first.demoMongo.dtos.MovieOutputDto;
import com.first.demoMongo.dtos.QueryMovieInputDto;
import com.first.demoMongo.dtos.validations.DirectionValidate;
import com.first.demoMongo.exceptions.BadRequestException;
import com.first.demoMongo.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@RequestMapping(MovieResorce.MOVIE)
@PreAuthorize("hasRole('ADMIN')")

public class MovieResorce {

    public static final String ID = "/{id}";
    public static final String SUGGESTIONS = "/suggestions";
    public static final String NAME = "/{name}";
    public static final String BY_NAME = "/name";
    public static final String MOVIE = "/movie";
    public static final String FILTER = "/filter";
    public static final String POSTER = "/poster";
    public static final String RELATED = "/related";

    @Autowired
    MoviesController moviesController;

    @GetMapping(ID)
    @PreAuthorize("permitAll()")
    public MovieOutputDto getMovieById(@PathVariable String id) throws NotFoundException {
        return this.moviesController.getMovieById(id);
    }

    @GetMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public Page<Movie> getPage(@RequestParam @Min(0) @Max(1000) int page, @RequestParam @Min(0) @Max(100) int size,
                               @RequestParam String key, @RequestParam @DirectionValidate String dir) throws NotFoundException {
        return this.moviesController.getPage(page, size, key, Sort.Direction.fromString(dir));
    }

    @GetMapping(value = FILTER + BY_NAME + NAME)
    @PreAuthorize("permitAll()")
    public Page<MovieMinimumOutputDto> getMinimunMoviesDtoByName(@PathVariable String name, int page, int size) throws NotFoundException{
       return this.moviesController.getMinimunMoviesDtoByName(name,page,size);
    }

    @GetMapping(FILTER)
    @PreAuthorize("permitAll()")
    public Page<MovieMinimumOutputDto> getMoviesbyQueryDto
            (QueryMovieInputDto filters,
             @RequestParam @Min(0) @Max(1000) int page,
             @RequestParam @Min(0)@Max(5) int size) {

        return this.moviesController.getMoviesByQueryDto(filters, page, size);
    }

    @GetMapping(SUGGESTIONS + NAME)
    @PreAuthorize("permitAll()")
    public List<String[]> getSuggestionsByName(@PathVariable String name) throws NotFoundException {
        return this.moviesController.getByName(name);
    }

    @GetMapping(RELATED + ID)
    @PreAuthorize("permitAll()")
    public Page<MovieMinimumOutputDto> getRelatedMovies(@PathVariable String id, @RequestParam int page, @RequestParam int size) throws NotFoundException {
        return this.moviesController.getRelatedMovies(id, page, size);
    }

    @GetMapping(value = BY_NAME + NAME)
    @PreAuthorize("permitAll()")
    public List<MovieOutputDto> getMoviesByName(@PathVariable String name) throws NotFoundException {
        return this.moviesController.getMoviesByName(name);
    }

    @PutMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public void updatePoster(@RequestParam String id, @RequestBody MovieInputDto movie) throws NotFoundException {
        this.moviesController.updateMovie(id, movie);
    }

    @PostMapping()
    @PreAuthorize("hasRole('ADMIN')")
    public MovieMinimumOutputDto createMovie(@RequestBody @Valid MovieInputDto movie) {
        return this.moviesController.createMovie(movie);
    }

    @PutMapping(POSTER)
    @PreAuthorize("hasRole('ADMIN')")
    public void updatePoster(@RequestParam String id, @RequestParam String poster) throws NotFoundException {
        this.moviesController.updatePoster(id, poster);
    }

    @PutMapping(value = ID)
    @PreAuthorize("hasRole('ADMIN')")
    public void updateMovie(@PathVariable String id, @Valid @RequestBody MovieInputDto movieInputDto) throws NotFoundException {
        this.moviesController.updateMovie(id, movieInputDto);
    }

    @DeleteMapping(value = ID)
    @PreAuthorize("hasRole('ADMIN')")
    public void delteMovie(@PathVariable String id) {
        moviesController.deleteMovie(id);
    }

}
