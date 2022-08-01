package com.example.springboot.controller.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class UserDTO {
    private String username;
    private String password;
    private String name;
    private String token;
    private String sex;
    private String phone;
    private String qq;
    private String wechat;
    private String school;
    private String subject;
    private String everything;
    private String birthday;
    private String email;
    @TableField(value = "avatar")
    private String avatarUrl;
}
