package com.example.modulex.service;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.example.modulex.domain.UserCreated;

@Service
public class UsersService {

    private final ApplicationEventPublisher events;

    public UsersService(ApplicationEventPublisher events) {
        this.events = events;
    }

    public String getUserById(String id) {
        // Dummy implementation
        return "{ userId: " + id + " }";
    }

    public void newUser(String userData) {
        // Logic to create a new user
        events.publishEvent(new UserCreated(userData));
    }

}
