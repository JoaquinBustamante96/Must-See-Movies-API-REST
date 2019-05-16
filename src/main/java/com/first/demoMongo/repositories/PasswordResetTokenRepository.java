package com.first.demoMongo.repositories;

import com.first.demoMongo.documents.PasswordResetToken;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface PasswordResetTokenRepository extends MongoRepository<PasswordResetToken, String>{

    @Query("{'user.$id':?0}")
    PasswordResetToken findTokenByUserId(String id);

}
