package com.carD.demo.controller;

import com.carD.demo.model.Request.LoginRequest;
import com.carD.demo.model.User;
import com.carD.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/auth/users")
public class UserController {
    private UserService userService;

    @Autowired // dependency injection
    public void setUserService(UserService userService){
        this.userService = userService;
    }

    // http://localhost:PORTNUMBER/auth/users/register
    @PostMapping(path = "/register")
    public User createUser(@RequestBody User userObject){
        System.out.println("Calling createUser()");
        return userService.createUser(userObject);
    }

    // http://localhost:PORTNUMBER/auth/users/login
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
        System.out.println("Calling loginUser()");
        return userService.loginUser(loginRequest);
    }
}
