package com.first.demoMongo.repositories;

import com.first.demoMongo.documents.PasswordResetToken;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PasswordResetTokenRepository extends MongoRepository<PasswordResetToken, String>{

}
