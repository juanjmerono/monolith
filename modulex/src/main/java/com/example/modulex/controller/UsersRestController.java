package com.example.modulex.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.modulex.service.UsersService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/user")
public class UsersRestController {

    private final UsersService usersService;

    public UsersRestController(UsersService usersService) {
        this.usersService = usersService;
    }

    // Endpoint get user
    @GetMapping("/{id}")
    public String getUser(@PathVariable String id) {
        return usersService.getUserById(id);
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public String postUser(@RequestBody String entity) {
        usersService.newUser(entity);   
        return entity;
    }
    

}
