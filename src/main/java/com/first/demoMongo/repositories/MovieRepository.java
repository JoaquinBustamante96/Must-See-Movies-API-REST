package com.first.demoMongo.repositories;

import com.first.demoMongo.documents.Movie;
import com.first.demoMongo.dtos.MovieMinimumOutputDto;
import com.first.demoMongo.dtos.MovieOutputDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import java.util.List;
import java.util.Optional;
import java.time.LocalDate;

public interface MovieRepository extends MongoRepository<Movie,String> {

     @Query("{'name':{$regex:?0,$options:'i'}}")
     Optional<List<MovieMinimumOutputDto>> findBynameContaining(String name, Pageable pageable);

     @Query("{'name':{$regex:?0,$options:'i'}}")
     Optional<List<MovieOutputDto>> findBynameContaining(String name);

     @Query("{'releaseDate': { $gte : ?0, $lt : ?1}}")
     List<Movie> findDate(LocalDate start, LocalDate end);

     @Query("{$and:["+
             "{'artMovement':{$regex:?0,$options:'i'}}," +
             "{'genre':{ $all: ?1}},"+
             "{'country':{$regex:?2,$options:'i'}}," +
             "{'language':{$regex:?3,$options:'i'}},"+
             "{'runtime':{ $gt: ?4, $lt : ?5 }}, " +
             "{'color':{$regex:?6,$options:'i'}},"+
             "{'sound':{$regex:?7,$options:'i'}}," +
             "{'releaseDate':{ $gte : ?8, $lt : ?9}} ]}")
     Page<MovieMinimumOutputDto> findByfilters(String artMovement,
                                               String[] genre, String country,
                                               String language, int minRuntime,
                                               int maxRuntime, String color,
                                               String sound, LocalDate startDate,
                                               LocalDate endDate, Pageable pageable);

     @Query("{$and:["+
             "{'artMovement':{$regex:?0,$options:'i'}}," +
             "{'country':{$regex:?1,$options:'i'}}," +
             "{'language':{$regex:?2,$options:'i'}},"+
             "{'runtime':{ $gt: ?3, $lt : ?4 }}, " +
             "{'color':{$regex:?5,$options:'i'}},"+
             "{'sound':{$regex:?6,$options:'i'}}," +
             "{'releaseDate':{ $gte : ?7, $lt : ?8}} ]}")
     Page<MovieMinimumOutputDto> findByfiltersExceptGenre(String artMovement, String country,
                                                          String language, int minRuntime,
                                                          int maxRuntime, String color,
                                                          String sound, LocalDate startDate,
                                                          LocalDate endDate, Pageable pageable);

}
