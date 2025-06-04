package com.mongodb.restapi.tutorial.controller;

import com.mongodb.restapi.tutorial.entity.UserEntity;
import com.mongodb.restapi.tutorial.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping("/user-list")
    public ResponseEntity<?> userList(){
        List<UserEntity> users = userService.getUserList();
        if(users != null && !users.isEmpty()){
            return new ResponseEntity<>(users, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/create-admin-user")
    public ResponseEntity<?> createAdminUser(@RequestBody UserEntity user){
        userService.saveAdminUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
