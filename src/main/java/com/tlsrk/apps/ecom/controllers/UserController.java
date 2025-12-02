package com.tlsrk.apps.ecom.controllers;

import com.tlsrk.apps.ecom.dto.User;
import com.tlsrk.apps.ecom.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    ResponseEntity<List<User>> responseList = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

    ResponseEntity<User> response = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

    @GetMapping("/api/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> userList = userService.getAllUsers();
        if (userList != null && userList.size() > 0) {
            responseList = new ResponseEntity<List<User>>(userList, HttpStatus.OK);
        }
        return responseList;
    }

    @GetMapping("/api/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id){
       User user = userService.getUserById(id);
       if (user !=null){
           response = new ResponseEntity<User>(user, HttpStatus.OK);
        }
        return response;
    }

    @PostMapping("/api/users")
    public ResponseEntity<User> createUser(@RequestBody User user){
        User savedUser = userService.addUser(user);
        return  new ResponseEntity("User Created Successfully With ID "+savedUser.getId(), HttpStatus.CREATED);
    }

    @PutMapping("/api/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user){
        User updatedUser = userService.updateUser(id, user);
        return  new ResponseEntity("User Updated Successfully For ID "+updatedUser.getId(), HttpStatus.OK);

    }

}
