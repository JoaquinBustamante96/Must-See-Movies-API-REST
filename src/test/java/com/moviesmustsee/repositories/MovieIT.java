package com.moviesmustsee.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.moviesmustsee.dataServices.DatabaseSeederService;
import com.moviesmustsee.documents.Movie;
import com.moviesmustsee.dtos.MovieMinimumOutputDto;
import com.moviesmustsee.dtos.MovieOutputDto;
import com.moviesmustsee.exceptions.NotFoundException;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:test.properties")
public class MovieIT {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private DatabaseSeederService databaseSeederService;

    @Test
    public void testfindBynameContaining() {
        String name = "rueba";
        List<MovieOutputDto> movieOutputDto = movieRepository.findBynameContaining(name).get();
        assertNotNull(movieOutputDto);
        assertTrue(movieOutputDto.size() > 0);
        for (MovieOutputDto movie : movieOutputDto) {
            assertTrue(Arrays.toString(movie.getName()).toLowerCase().contains(name));
        }
    }

    @Test
    public void findByFilters() {

        Movie movie = this.movieRepository.findById("0003").get();
        movie.setReleaseDate(LocalDate.of(1970, 02, 02));
        this.movieRepository.save(movie);

        String name = "prueba";
        String artMovement = "artmovement";
        String[] genre = {"genre1"};
        String country = "country";
        String region = "EUROPA";
        String language = "language";
        int minRuntime = 100;
        int maxRuntime = 200;
        String color = "true";
        String sound = "true";
        LocalDate startDate = LocalDate.of(1960, 2, 3);
        LocalDate endDate = LocalDate.of(2600, 2, 3);

        Page<MovieMinimumOutputDto> movies = this.movieRepository.findByfilters(
                artMovement, genre, country, region, language, minRuntime, maxRuntime, color, sound, startDate, endDate, PageRequest.of(0, 5));

        assertTrue(Arrays.toString(movies.getContent().get(0).getName()).contains(name));

    }

    @Test
    public void findBygenre() {
        Page<MovieMinimumOutputDto> movieMinimumOutputDtoPage = this.movieRepository.findBygenre("asd", PageRequest.of(0, 3));
        assertTrue(movieMinimumOutputDtoPage.getContent().size() <= 3);
    }

    @Test
    public void getMinimunMoviesDtoByName() throws NotFoundException {
        Page<MovieMinimumOutputDto> movieMinimumOutputDtoPage = this.movieRepository
                .findBynameContaining("prueba", PageRequest.of(0, 2)).get();

        movieMinimumOutputDtoPage.forEach(
                movieMinimumOutputDto -> {
                    assertTrue(Arrays.toString(movieMinimumOutputDto.getName()).contains("prueba"));
                }
        );
    }

    @Test
    public void getRelatedByArtMovementAndGenre() {
        Page<MovieMinimumOutputDto> movieMinimumOutputDtosPage = this.movieRepository
                .findRelatedByArtMovementOrGenre("9", "French Impressionism", "Drama", PageRequest.of(0, 5));
        movieMinimumOutputDtosPage.forEach(movieMinimumOutputDto -> System.out.println(Arrays.toString(movieMinimumOutputDto.getName())));
    }

    @Test
    public void findPageOfIds() {
        String[] ids = {"12", "13"};
        this.movieRepository.findPageOfIds(ids, PageRequest.of(0, 3)).forEach(
                value -> assertTrue(Arrays.toString(ids).contains(value.getId()))
        );
    }


    @After
    public void resetDB() {
        this.databaseSeederService.resetDB();
    }

}
