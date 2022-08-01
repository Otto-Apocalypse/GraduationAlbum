package com.example.springboot.controller.dto;

import lombok.Data;

@Data
public class TeacherDTO {
    private String username;
    private String password;
    private String token;
    private String name;
    private String subject;
    private String birthday;
    private String phone;
}
