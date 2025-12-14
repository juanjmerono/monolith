package com.example.moduley.notifications.service;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.moduley.notifications.domain.NotificationSent;

@Service
public class NotificationService {

    private final ApplicationEventPublisher events;

    public NotificationService(ApplicationEventPublisher events) {
        this.events = events;
    }

    @Transactional
    public void newNotification(String notificationData) {
        // Logic to create a new user
        events.publishEvent(new NotificationSent(notificationData));
    }

}
