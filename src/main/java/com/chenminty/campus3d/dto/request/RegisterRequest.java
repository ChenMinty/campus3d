package com.chenminty.campus3d.dto.request;

import lombok.Data;

@Data
public class RegisterRequest {
    private Long userId;

    private String username;

    private String password;

    private String email;
}
