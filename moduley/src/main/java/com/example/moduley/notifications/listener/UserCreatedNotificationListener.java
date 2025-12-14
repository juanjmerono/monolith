package com.example.moduley.notifications.listener;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

import com.example.modulex.domain.UserCreated;
import com.example.moduley.notifications.domain.NotificationSent;

@Component
public class UserCreatedNotificationListener {

    @Async
    @TransactionalEventListener
    public void onApplicationEvent(UserCreated event) {
        System.out.println("UserCreated event received: " + event.getSource());
    }

    @Async
    @TransactionalEventListener
    public void onApplicationEvent(NotificationSent event) {
        System.out.println("NotificationSent event received: " + event.getSource());
    }

}
