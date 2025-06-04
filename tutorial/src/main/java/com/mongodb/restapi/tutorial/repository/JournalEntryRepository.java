package com.mongodb.restapi.tutorial.repository;

import com.mongodb.restapi.tutorial.entity.JournalEntryEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

@Component
public interface JournalEntryRepository extends MongoRepository<JournalEntryEntity, ObjectId> {
}
