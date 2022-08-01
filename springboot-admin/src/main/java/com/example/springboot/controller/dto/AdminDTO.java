package com.example.springboot.controller.dto;

import lombok.Data;

@Data
public class AdminDTO {
    private String username;
    private String password;
    private String token;
}
