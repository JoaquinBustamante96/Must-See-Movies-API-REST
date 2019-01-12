package com.first.demoMongo.repositories;

import com.first.demoMongo.documents.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface UserRepository extends MongoRepository<User, String> {

    public User findByEmail(String email);

    public User findByusername(String userName);

    public User findBydni(String dni);

    @Query("{ 'token.value' : ?0 }")
    public User findByTokenValue(String tokenValue);

}
