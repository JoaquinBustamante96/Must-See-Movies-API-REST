package com.moviesmustsee.repositories;

import com.moviesmustsee.documents.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface UserRepository extends MongoRepository<User, String> {

    public User findByemail(String email);

    public User findByusername(String userName);

    @Query("{ 'token.value' : ?0 }")
    public User findByTokenValue(String tokenValue);

}
