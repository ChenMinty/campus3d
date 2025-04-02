package com.chenminty.campus3d.dto.response;

import lombok.Data;

@Data
public class LoginResponse {
    private Long userId;
    private String token;
    private String username;
}
