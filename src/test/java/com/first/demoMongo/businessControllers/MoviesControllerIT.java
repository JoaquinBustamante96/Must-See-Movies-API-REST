package com.first.demoMongo.businessControllers;

import com.first.demoMongo.dataServices.DatabaseSeederService;
import com.first.demoMongo.documents.Movie;
import com.first.demoMongo.dtos.MovieInputDto;
import com.first.demoMongo.dtos.MovieMinimunOutputDto;
import com.first.demoMongo.dtos.MovieOutputDto;
import com.first.demoMongo.exceptions.NotFoundException;
import com.first.demoMongo.repositories.MovieRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:test.properties")
public class MoviesControllerIT {

    @Autowired
    private MoviesController moviesController;
    @Autowired
    private DatabaseSeederService databaseSeederService;
    private MovieInputDto movieInputDto;
    @Autowired
    private MovieRepository movieRepository;

    @Before
    public void createMovieInputDto(){
        this.databaseSeederService.initializeDB();
        String[] array = new String[]{"first","second"};
        this.movieInputDto = new MovieInputDto(array, LocalDate.of(1982,3,5), "artmovement", array,
                "storyline", array, "country", "lenguage",
        14, true, true, "trailer","poster");
    }

    @Test
    public void createMovie(){
        MovieMinimunOutputDto movieMinimunOutputDto = this.moviesController.createMovie(this.movieInputDto);
        assertEquals(Arrays.toString(movieMinimunOutputDto.getName()),Arrays.toString(this.movieInputDto.getName()));
    }

    @Test
    public void updatePoster() throws NotFoundException {
        this.moviesController.updatePoster("0001","updatedPoster");
        Movie movie = this.movieRepository.findById("0001").get();
        assertEquals(movie.getPoster(),"updatedPoster");
    }

    @Test
    public void updateMovie() throws NotFoundException{
        this.moviesController.updateMovie("0001",this.movieInputDto);
        Movie movie = this.movieRepository.findById("0001").get();
        assertEquals(Arrays.toString(movie.getName()),Arrays.toString(this.movieInputDto.getName()));
        assertEquals(movie.getReleaseDate(),this.movieInputDto.getReleaseDate());
        assertEquals(movie.getArtMovement(),this.movieInputDto.getArtMovement());
        assertEquals(movie.getCountry(),this.movieInputDto.getCountry());
        assertEquals(movie.getLanguage(),this.movieInputDto.getLanguage());
        assertEquals(movie.getStoryline(),this.movieInputDto.getStoryline());
        assertEquals(movie.getColor(),this.movieInputDto.getColor());
        assertEquals(movie.getSound(),this.movieInputDto.getSound());
        assertEquals(Arrays.toString(movie.getGenre()),Arrays.toString(this.movieInputDto.getGenre()));
        assertEquals(Arrays.toString(movie.getDirector()),Arrays.toString(this.movieInputDto.getDirector()));
        assertEquals(movie.getRuntime(),this.movieInputDto.getRuntime());
        assertEquals(movie.getTrailer(),this.movieInputDto.getTrailer());

    }

    @Test
    public void getMoviesListByName() throws NotFoundException{
        String name = "prueba";
        List<MovieOutputDto> moviesNames = this.moviesController.getMoviesByName(name);
        assertTrue(moviesNames.size()>0);
        moviesNames.forEach(MovieOutputDto -> {
            assertTrue(Arrays.toString(MovieOutputDto.getName()).contains(name));
        });
    }

    @Test
    public void getByName() throws NotFoundException{
        String name = "prueba";
        List<String[]> moviesNames = this.moviesController.getByName(name);
        assertTrue(moviesNames.size()<6);
        for (String[] names: moviesNames) {
            assertTrue(Arrays.toString(names).toLowerCase().contains(name));
        }
    }

    @After
    public void resetDB(){
        this.databaseSeederService.resetDB();
    }

}
