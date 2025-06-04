package com.mongodb.restapi.tutorial.services;

import com.mongodb.restapi.tutorial.entity.JournalEntryEntity;
import com.mongodb.restapi.tutorial.entity.UserEntity;
import com.mongodb.restapi.tutorial.repository.JournalEntryRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;

    @Transactional
    public void createEntry(JournalEntryEntity myEntry, String username){
        try {
            myEntry.setCreatedDate(LocalDateTime.now());
            JournalEntryEntity savedRecord = journalEntryRepository.save(myEntry);
            UserEntity user = userService.findByUserName(username);
            user.getJournalEntryEntities().add(savedRecord);
            userService.saveUser(user);
        }catch (Exception e){
            System.out.println(e);
            throw new RuntimeException("An error has been occurred:", e);
        }

    }

    public List<JournalEntryEntity> getAllEntry(){
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntryEntity> findById(ObjectId id){
        return journalEntryRepository.findById(id);
    }

    @Transactional
    public void deleteById(ObjectId id, String username){
        try {
            UserEntity user = userService.findByUserName(username);
            boolean isRemoved = user.getJournalEntryEntities().removeIf(entry -> entry.getId().equals(id));
            if(isRemoved){
                journalEntryRepository.deleteById(id);
                userService.saveUser(user);
            }
        }catch(Exception e){
            System.out.println(e);
            throw new RuntimeException("An error occurred while deleting an journal entry");
        }
    }

}
