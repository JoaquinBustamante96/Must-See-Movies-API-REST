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

     @Query("{'releaseDate': { $gte : ?0, $lt : ?1}}")
     List<Movie> findDate(LocalDate start, LocalDate end);

     @Query("{$and:[{'name':{$regex:?0,$options:'i'}}," +
             "{'artMovement':{$regex:?1,$options:'i'}}," +
             "{'genre':{$regex:?2,$options:'i'}}," +
             "{'country':{$regex:?3,$options:'i'}}," +
             "{'lenguage':{$regex:?4,$options:'i'}}," +
             "{'runtime':{ $gt: ?5, $lt : ?6 }}," +
             "{'color':?7},"+
             "{'sound':?8}," +
             "{'releaseDate':{ $gte : ?9, $lt : ?10} }]}")
     Optional<List<MovieMinimunOutputDto>> findByfilters(String name,String artMovement,
                                                  String genre,String country,
                                                  String lenguage,int minRuntime,
                                                  int maxRuntime, boolean color,
                                                  boolean sound, LocalDate startDate,
                                                  LocalDate endDate);

}
