package com.example.modulex.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.example.modulex.domain.UserCreated;

@Component
public class UserCreatedListener implements ApplicationListener<UserCreated> {

    @Override
    public void onApplicationEvent(UserCreated event) {
        System.out.println("UserCreated event received: " + event.getSource());
    }

}
