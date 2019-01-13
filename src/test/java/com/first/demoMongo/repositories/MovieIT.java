package com.first.demoMongo.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.first.demoMongo.dtos.MovieMinimunOutputDto;
import com.first.demoMongo.dtos.MovieOutputDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.constraints.AssertTrue;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:test.properties")
public class MovieIT {

    @Autowired
    private MovieRepository movieRepository;

    @Test
    public void testfindBynameContaining(){
        String name = "prueba";
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

}
