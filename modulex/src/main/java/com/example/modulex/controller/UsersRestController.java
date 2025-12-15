package com.example.modulex.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.modulex.service.UsersService;

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

    @Value("${spring.datasource.url}")
    private String URL;
    @Value("${spring.datasource.username}")
    private String USER;
    @Value("${spring.datasource.password}")
    private String PASSWORD;

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
    

}
