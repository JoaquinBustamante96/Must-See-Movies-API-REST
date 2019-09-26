package com.moviesmustsee.repositories;

import com.moviesmustsee.documents.MovieLists;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface MovieListsRepository extends MongoRepository<MovieLists,String> {
    @Query("{'user.id':?0}")
    MovieLists findListsByUserId(String id);
}
