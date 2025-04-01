package com.chenminty.campus3d.entity;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    private Long userId;
    private String username;
    private String passwordHash;
    private String email;
    private String role;        // "USER" or "ADMIN"
    private String avatarUrl;
    private Date createdAt;
    private Date updatedAt;
    private Date lastLoginAt;   // 最后登录时间
}
