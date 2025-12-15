package com.example.modulex.domain;

import org.springframework.modulith.events.Externalized;

@Externalized("com.example.modulex.domain.UserCreated::#{#this.getSource()}")
public class UserCreated {

    private final String source;

    public UserCreated(String source) {
        this.source = source;
    }

    public String getSource() {
        return source;
    }

}
