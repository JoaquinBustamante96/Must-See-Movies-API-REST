package com.moviesmustsee.repositories;

import com.moviesmustsee.documents.Movie;
import com.moviesmustsee.dtos.MovieMinimumOutputDto;
import com.moviesmustsee.dtos.MovieOutputDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;
import java.time.LocalDate;

public interface MovieRepository extends MongoRepository<Movie, String> {

    @Query("{'name':{$regex:?0,$options:'i'}}")
    Optional<Page<MovieMinimumOutputDto>> findBynameContaining(String name, Pageable pageable);

    @Query("{'name':{$regex:?0,$options:'i'}}")
    Optional<List<MovieOutputDto>> findBynameContaining(String name);

    @Query("{'id':{ $in: ?0 }}")
    Page<MovieMinimumOutputDto> findPageOfIds(String[] Id, Pageable pageable);

    @Query("{'releaseDate': { $gte : ?0, $lt : ?1}}")
    List<Movie> findDate(LocalDate start, LocalDate end);

    @Query("{$and:[ {'id':{ $not: {$regex:?0,$options:'i'} }}," +
            "{$or: [ {'artMovement':{$regex:?1,$options:'i'}},{'genre':{$regex:?2,$options:'i'}} ]} " +
            "]}")
    Page<MovieMinimumOutputDto> findRelatedByArtMovementOrGenre(String id, String artMovement, String genre, Pageable pageable);

    @Query("{'genre':?0}")
    Page<MovieMinimumOutputDto> findBygenre(String genre, Pageable pageable);

    @Query("{$and:[" +
            "{'artMovement':{$regex:?0,$options:'i'}}," +
            "{'genre':{ $all: ?1}}," +
            "{'country':{$regex:?2,$options:'i'}}," +
            "{'region':{$regex:?3,$options:'i'}}," +
            "{'language':{$regex:?4,$options:'i'}}," +
            "{'runtime':{ $gt: ?5, $lt : ?6 }}, " +
            "{'color':{$regex:?7,$options:'i'}}," +
            "{'sound':{$regex:?8,$options:'i'}}," +
            "{'releaseDate':{ $gte : ?9, $lt : ?10}} ]}")
    Page<MovieMinimumOutputDto> findByfilters(String artMovement,
                                              String[] genre, String country,
                                              String region, String language,
                                              int minRuntime, int maxRuntime,
                                              String color, String sound,
                                              LocalDate startDate, LocalDate endDate,
                                              Pageable pageable);

    @Query("{$and:[" +
            "{'artMovement':{$regex:?0,$options:'i'}}," +
            "{'country':{$regex:?1,$options:'i'}}," +
            "{'region':{$regex:?2,$options:'i'}}," +
            "{'language':{$regex:?3,$options:'i'}}," +
            "{'runtime':{ $gt: ?4, $lt : ?5 }}, " +
            "{'color':{$regex:?6,$options:'i'}}," +
            "{'sound':{$regex:?7,$options:'i'}}," +
            "{'releaseDate':{ $gte : ?8, $lt : ?9}} ]}")
    Page<MovieMinimumOutputDto> findByfiltersExceptGenre(String artMovement, String country,
                                                         String region, String language,
                                                         int minRuntime, int maxRuntime, String color,
                                                         String sound, LocalDate startDate,
                                                         LocalDate endDate, Pageable pageable);

}
