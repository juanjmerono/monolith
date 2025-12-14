package com.example.modulex.domain;

import org.springframework.context.ApplicationEvent;

public class UserCreated extends ApplicationEvent{

    public UserCreated(Object source) {
        super(source);
    }

}
