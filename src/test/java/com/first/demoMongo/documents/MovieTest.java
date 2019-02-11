package com.first.demoMongo.documents;

import org.junit.Test;

import java.time.LocalDate;
import static org.junit.Assert.assertEquals;

public class MovieTest {

    @Test
    public void testYear(){

        String[] array = new String[]{"first","second"};
        Movie movie = new Movie(array,array,"asdsad", "artmovement", array,
                "storyline", "country", LocalDate.of(1993,03,04),
                14, "true", "true", "trailer","poster");

       assertEquals(movie.getReleaseDate(),LocalDate.of(1993,03,04));
    }

}
