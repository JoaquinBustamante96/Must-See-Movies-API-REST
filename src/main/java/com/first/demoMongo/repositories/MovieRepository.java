package com.first.demoMongo.repositories;

import com.first.demoMongo.documents.Movie;
import com.first.demoMongo.dtos.MovieMinimunOutputDto;
import com.first.demoMongo.dtos.MovieOutputDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import java.util.List;
import java.util.Optional;

public interface MovieRepository extends MongoRepository<Movie,String> {

     List<MovieOutputDto> findBygenre(String gnere);

     @Query("{'$or':[ {'name':{$regex:?0,$options:'i'}}] }")
     List<MovieOutputDto> findByname(String name);

     @Query("{'$or':[ {'name':{$regex:?0,$options:'i'}}] }")
     Optional<List<MovieMinimunOutputDto>> findByname(String name, Pageable pageable);

     @Query("{'$or':[ {'name':{$regex:?0,$options:'i'}}] }")
     Optional<List<Movie>> findBynameContaining(String name);

     Optional<List<MovieMinimunOutputDto>> findBydirectorContaining(String director, Pageable pageable);

}
