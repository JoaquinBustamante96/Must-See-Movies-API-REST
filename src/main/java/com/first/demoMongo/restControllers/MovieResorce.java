package com.first.demoMongo.restControllers;

import com.first.demoMongo.businessControllers.MoviesController;
import com.first.demoMongo.documents.Movie;
import com.first.demoMongo.dtos.MovieInputDto;
import com.first.demoMongo.dtos.MovieMinimumOutputDto;
import com.first.demoMongo.dtos.MovieOutputDto;
import com.first.demoMongo.dtos.QueryMovieInputDto;
import com.first.demoMongo.exceptions.BadRequestException;
import com.first.demoMongo.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.IntStream;

@PreAuthorize("hasRole('USER')")
@RestController
@RequestMapping(MovieResorce.MOVIE)
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
    public MovieOutputDto getMovieById(@PathVariable String id) throws NotFoundException {
        return this.moviesController.getMovieById(id);
    }

    @GetMapping()
    public Page<Movie> getPage(@RequestParam int page, @RequestParam int size,
                               @RequestParam String key, @RequestParam String dir) throws BadRequestException {
        final int[] allowedSizes = {25, 50, 75, 100};
        if (!IntStream.of(allowedSizes).anyMatch(x -> x == size)) {
            throw new BadRequestException("Size: " + size + " is not allowed, allowed sizes are: 25,50,75,100");
        }
        Sort.Direction direction = Sort.Direction.fromOptionalString(dir)
                .orElseThrow(() -> new BadRequestException("Dir not Allowed: " + dir + " allowed dir are asc or desc"));

        return this.moviesController.getPage(page, size, key, direction);
    }

    @GetMapping(FILTER)
    public Page<MovieMinimumOutputDto> getMoviesbyQueryDto(QueryMovieInputDto filters,
                                                           @RequestParam int page, @RequestParam int size) throws NotFoundException, BadRequestException {
        if (size > 5 || size < 0) {
            throw new BadRequestException("Page size not allowed exception: Size" + size + "is not allowed, max allowed page size is " + 5);
        }
        return this.moviesController.getMoviesByQueryDto(filters, page, size);
    }

    @GetMapping(SUGGESTIONS + NAME)
    public List<String[]> getSuggestionsByName(@PathVariable String name) throws NotFoundException {
        return this.moviesController.getByName(name);
    }

    @GetMapping(MOVIE + RELATED + ID)
    public Page<MovieMinimumOutputDto> getRelatedMovies(@PathVariable String id, @RequestParam int page, @RequestParam int size) throws NotFoundException {
        return this.moviesController.getRelatedMovies(id, page, size);
    }

    @GetMapping(value = BY_NAME + NAME)
    public List<MovieOutputDto> getMoviesByName(@PathVariable String name) throws NotFoundException {
        return this.moviesController.getMoviesByName(name);
    }

    @PutMapping()
    public void updatePoster(@RequestParam String id, @RequestBody MovieInputDto movie) throws NotFoundException {
        this.moviesController.updateMovie(id, movie);
    }

    @PostMapping()
    public MovieMinimumOutputDto createMovie(@RequestBody MovieInputDto movie) {
        return this.moviesController.createMovie(movie);
    }

    @PutMapping(POSTER)
    public void updatePoster(@RequestParam String id, @RequestParam String poster) throws NotFoundException {
        this.moviesController.updatePoster(id, poster);
    }

    @PutMapping(value = ID)
    public void updateMovie(@PathVariable String id, @Valid @RequestBody MovieInputDto movieInputDto) throws MethodArgumentNotValidException, NotFoundException {
        this.moviesController.updateMovie(id, movieInputDto);
    }

    @DeleteMapping(value = ID)
    public void delteMovie(@PathVariable String id) {
        moviesController.deleteMovie(id);
    }

}
