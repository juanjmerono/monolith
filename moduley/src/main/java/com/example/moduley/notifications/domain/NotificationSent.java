package com.example.moduley.notifications.domain;

public class NotificationSent {

    private final String source;

    public NotificationSent(String source) {
        this.source = source;
    }

    public String getSource() {
        return source;
    }
    
}
