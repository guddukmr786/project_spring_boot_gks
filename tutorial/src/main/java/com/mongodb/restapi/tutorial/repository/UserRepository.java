package com.mongodb.restapi.tutorial.repository;

import com.mongodb.restapi.tutorial.entity.UserEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<UserEntity, ObjectId> {
    UserEntity findByUserName(String username);
    UserEntity deleteByUserName(String userName);
}
