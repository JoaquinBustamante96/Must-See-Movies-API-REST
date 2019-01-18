package com.first.demoMongo.restControllers;

import com.first.demoMongo.businessControllers.MoviesController;
import com.first.demoMongo.documents.Movie;
import com.first.demoMongo.dtos.MovieInputDto;
import com.first.demoMongo.dtos.MovieMinimunOutputDto;
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
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.IntStream;

@PreAuthorize("hasRole('USER')")
@RestController
@RequestMapping(movieResorce.crud)
public class movieResorce {

    public static final String crud = "/crudMovies";
    public static final String ID = "/{id}";
    public static final String NAME = "/{name}";
    public static final String PRUEBA = "/prueba";
    public static final String MOVIE = "/movie";
    public static final String POSTER = "/poster";

    @Autowired
    MoviesController moviesController;

    @GetMapping(movieResorce.PRUEBA)
    public Page<Movie> getPage(@RequestParam int page, @RequestParam int size,
                               @RequestParam String key, @RequestParam String dir )throws BadRequestException {
        final int [] allowedSizes = {25,50,75,100};
        if(!IntStream.of(allowedSizes).anyMatch(x -> x == size)) {
            throw new BadRequestException("Size: " + size +" or page "+page+ " is not allowed, allowed sizes are: 25,50,75,100");
        }
        Sort.Direction direction = Sort.Direction.fromOptionalString(dir)
                .orElseThrow(()-> new BadRequestException("Dir not Allowed: "+dir+" allowed dir are asc or desc"));

        return this.moviesController.getPage(page,size,key,direction);
    }

    @GetMapping(MOVIE)
    public List<MovieMinimunOutputDto> getMoviesbyQueryDto(@RequestBody QueryMovieInputDto queryMovieInputDto){
        return this.moviesController.getMoviesByQueryDto(queryMovieInputDto);
    }

    @GetMapping(value = NAME)
    public List<String[]> getByName(@PathVariable String name)throws NotFoundException{
        return this.moviesController.getByName(name);
    }

    @GetMapping(value = MOVIE+NAME)
    public List<MovieOutputDto> getMoviesByName(@PathVariable String name) throws NotFoundException{
        return this.moviesController.getMoviesByName(name);
    }

    @PutMapping(value = MOVIE)
    public void updatePoster(@RequestParam String id,@RequestBody MovieInputDto movie)throws NotFoundException{
        this.moviesController.updateMovie(id,movie);
    }

    @PostMapping(value = MOVIE)
    public MovieMinimunOutputDto createMovie(@RequestBody MovieInputDto movie){
        return this.moviesController.createMovie(movie);
    }

    @PutMapping(value = MOVIE+POSTER)
    public void updatePoster(@RequestParam String id,@RequestParam String poster) throws  NotFoundException{
        this.moviesController.updatePoster(id,poster);
    }

    @PutMapping(value = ID)
    public void updateMovie(@PathVariable String id,@Valid @RequestBody MovieInputDto movieInputDto) throws MethodArgumentNotValidException,NotFoundException {
        this.moviesController.updateMovie(id,movieInputDto);
    }

    @DeleteMapping(value = ID)
    public void delteMovie(@PathVariable String id){
        moviesController.deleteMovie(id);
    }

}
