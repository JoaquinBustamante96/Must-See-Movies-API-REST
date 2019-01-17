package com.first.demoMongo.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.first.demoMongo.dataServices.DatabaseSeederService;
import com.first.demoMongo.documents.Movie;
import com.first.demoMongo.dtos.MovieMinimunOutputDto;
import com.first.demoMongo.dtos.MovieOutputDto;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:test.properties")
public class MovieIT {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private DatabaseSeederService databaseSeederService;

    @Test
    public void testfindBynameContaining(){
        String name = "rueba";
        List<MovieOutputDto> movieOutputDto = movieRepository.findBynameContaining(name).get();
        assertNotNull(movieOutputDto);
        assertTrue(movieOutputDto.size()>0);
        for (MovieOutputDto movie: movieOutputDto) {
           assertTrue(Arrays.toString(movie.getName()).toLowerCase().contains(name));
        }
    }

    @Test
    public void findBynameContainingPageable(){
        String name = "prueba";
        int size = 1;
        int page=0;
        List<MovieMinimunOutputDto> movieMinimunOutputDto = this.movieRepository.findBynameContaining(name, PageRequest.of(page,size)).get();
        assertEquals(movieMinimunOutputDto.size(),1);
        assertTrue(Arrays.toString(movieMinimunOutputDto.get(0).getName()).toLowerCase().contains(name));
    }

    @Test
    public void findByFilters(){

        Movie movie = this.movieRepository.findById("0003").get();
        movie.setReleaseDate(LocalDate.of(1970,02,02));
        this.movieRepository.save(movie);

        String name = "prueba";
        String artMovement = "artmovement";
        String genre = "genre";
        String country = "country";
        String lenguage = "lenguage";
        int minRuntime = 100;
        int maxRuntime = 200;
        boolean color = true;
        boolean sound = true;
        LocalDate startDate = LocalDate.of(1960,2,3);
        LocalDate endDate = LocalDate.of(2600,2,3);

        Optional<List<MovieMinimunOutputDto>> moviesOptional = this.movieRepository.findByfilters(
                name,artMovement,genre,country,lenguage,minRuntime,maxRuntime,color,sound,startDate,endDate);

        moviesOptional.ifPresent(movieList -> {
                    movieList.forEach( MovieMinimunOutputDto -> {
                        assertTrue(Arrays.toString(MovieMinimunOutputDto.getName()).contains(name));
                            }
                    );
                });
    }

    @After
    public void resetDB(){
        this.databaseSeederService.resetDB();
    }

}
