package com.first.demoMongo.businessControllers;

import com.first.demoMongo.dataServices.DatabaseSeederService;
import com.first.demoMongo.dtos.MovieInputDto;
import com.first.demoMongo.dtos.MovieMinimunOutputDto;
import com.first.demoMongo.exceptions.NotFoundException;
import com.first.demoMongo.repositories.MovieRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MoviesControllerIT {

    @Autowired
    private MoviesController moviesController;

    @Autowired
    private DatabaseSeederService databaseSeederService;

    private MovieInputDto movieInputDto;

    @Before
    public void createMovieInputDto(){
        String[] array = new String[]{"first","second"};
        this.movieInputDto = new MovieInputDto(array,"01-02-96", "artmovement", array,
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
    }

    @Test
    public void updateMovie() throws NotFoundException{
        this.moviesController.updateMovie("0001",this.movieInputDto);
    }

    @After
    public void resetDB(){
        this.databaseSeederService.resetDB();
    }

}
