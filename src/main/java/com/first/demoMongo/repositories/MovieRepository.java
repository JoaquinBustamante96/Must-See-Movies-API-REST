package com.first.demoMongo.repositories;

import com.first.demoMongo.documents.Movie;
import com.first.demoMongo.dtos.MovieMinimunOutputDto;
import com.first.demoMongo.dtos.MovieOutputDto;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import java.util.List;
import java.util.Optional;
import java.time.LocalDate;

public interface MovieRepository extends MongoRepository<Movie,String> {

     @Query("{'name':{$regex:?0,$options:'i'}}")
     Optional<List<MovieMinimunOutputDto>> findBynameContaining(String name, Pageable pageable);

     @Query("{'name':{$regex:?0,$options:'i'}}")
     Optional<List<MovieOutputDto>> findBynameContaining(String name);

     @Query("{'releaseDate':{$dateFromString: {dateString: ?0,format: '%Y-%m-%d' }}}")
     Optional<List<MovieMinimunOutputDto>> findBydate(String date);

     @Query("{'releaseDate': { $gte : ?0, $lt : ?1}}")
     List<Movie> findDate(LocalDate start, LocalDate end);

}
