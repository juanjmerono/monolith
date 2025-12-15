package com.example.moduley.notifications.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.moduley.notifications.service.NotificationService;

@RestController
@RequestMapping("/notification")
public class NotificationController {

    private final NotificationService notificationService;

    @Value("${spring.datasource.url}")
    private String URL;
    @Value("${spring.datasource.username}")
    private String USER;
    @Value("${spring.datasource.password}")
    private String PASSWORD;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }
    
    /*
    create table event_publication (
        id uuid not null,
        completion_date timestamp(6) with time zone,
        event_type varchar(255),
        listener_id varchar(255),
        publication_date timestamp(6) with time zone,
        serialized_event varchar(255),
        primary key (id)
    ) 
    */
    @GetMapping("/events")
    public List<Map<String, Object>> getEvents() {
        List<Map<String, Object>> events = new ArrayList<>();

        String sql = "SELECT * FROM event_publication";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()) {
            int len = rs.getMetaData().getColumnCount();
            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                for (int i = 1; i <= len; i++) {
                    String columnName = rs.getMetaData().getColumnName(i);
                    row.put(columnName, rs.getObject(columnName));
                }
                events.add(row);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return events;
    }

    @GetMapping("/send")
    public String getSend() {
        notificationService.newNotification("Example");
        return "Send Notification OK";
    }
    

}
