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
     Optional<List<MovieMinimunOutputDto>> findBynameContaining(String name, Pageable pageable);

     @Query("{'$or':[ {'name':{$regex:?0,$options:'i'}}] }")
     Optional<List<MovieOutputDto>> findBynameContaining(String name);
     
}
