package com.mongodb.restapi.tutorial.services;

import com.mongodb.restapi.tutorial.entity.UserEntity;
import com.mongodb.restapi.tutorial.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void saveNewUser(UserEntity user){
        try{
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(List.of("USER"));
            userRepository.save(user);
        }catch (Exception e){
            log.error("Error occurred for {} :",user.getUserName(), e);
            throw new RuntimeException("Duplicate entry!!!");
        }

    }

    public void saveUser(UserEntity user){
        userRepository.save(user);
    }

    public List<UserEntity> getUserList(){
        return userRepository.findAll();
    }

    public Optional<UserEntity> findById(ObjectId id){
        return userRepository.findById(id);
    }

    public UserEntity findByUserName(String username){
        return userRepository.findByUserName(username);
    }

    public void saveAdminUser(UserEntity user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(List.of("USER","ADMIN"));
        userRepository.save(user);
    }

}
