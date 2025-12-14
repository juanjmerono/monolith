package com.example.modulex.listener;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import com.example.modulex.domain.UserCreated;

@Component
public class UserCreatedListener {

    @Async
    @TransactionalEventListener
    public void onApplicationEvent(UserCreated event) {
        System.out.println("UserCreated event received: " + event.getSource());
    }

}
