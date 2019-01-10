package com.first.demoMongo.repositoriesTest;

import com.first.demoMongo.documents.Movie;
import com.first.demoMongo.repositories.MovieRepository;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MovieT {

    @Autowired
    MovieRepository movieRepository;

    @Before
    public void populate(){
        //Movie movie = new Movie("prueba","pruebagenre","puerbaStorlyline",new String[]{"pruebaDirector"},new String[]{"prueba"},new String[]{"pruebaIdioma"},new Date(),100,true,false,"trailer.com","post.com");
    }

    @Test
    public void test(){
        //assertTrue(movieRepository.findByname("prueba").size()>0);

    }

    @Test
    public void findByGeneretest(){
    }

    @Test
    public void findBynameOrDirector(){
    }

}
