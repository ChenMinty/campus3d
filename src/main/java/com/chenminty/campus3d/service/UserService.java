package com.chenminty.campus3d.service;

import com.chenminty.campus3d.dto.request.LoginRequest;
import com.chenminty.campus3d.dto.request.RegisterRequest;
import com.chenminty.campus3d.dto.response.LoginResponse;
import com.chenminty.campus3d.entity.User;
import com.chenminty.campus3d.exception.LoginFailedException;
import com.chenminty.campus3d.exception.RegistrationFailedException;
import com.chenminty.campus3d.mapper.UserMapper;
import com.chenminty.campus3d.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    public boolean register(RegisterRequest request) {
        // 学号唯一性校验
        if (userMapper.existsByUserId(request.getUserId())) {
            throw new RegistrationFailedException("该学号已被注册过");
        }

        // 邮箱唯一
        if (userMapper.existsByEmail(request.getEmail())) {
            throw new RegistrationFailedException("该邮箱已被注册过");
        }

        User user = new User();
        user.setUserId(request.getUserId());
        user.setUsername(request.getUsername());
        user.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        user.setRole("USER");

        return userMapper.insertUser(user) > 0;
    }

    public LoginResponse login(LoginRequest request) {
        // 1. 查询用户
        User user = userMapper.findByUserId(Long.parseLong(request.getUserId()));
        if (user == null) {
            throw new LoginFailedException("用户不存在");
        }

        // 2. 验证密码
        if (!passwordEncoder.matches(request.getPassword(), user.getPasswordHash())) {
            throw new LoginFailedException("密码错误");
        }

        // 3. 更新最后登录时间
        Date now = new Date();
        userMapper.updateLastLogin(user.getUserId(), now);

        // 4. 生成JWT令牌
        String token = jwtUtils.generateToken(
                user.getUserId(),
                List.of(user.getRole()) // 将角色转换为列表
        );

        // 5. 返回响应
        LoginResponse response = new LoginResponse();
        response.setUserId(user.getUserId());
        response.setToken(token);
        response.setUsername(user.getUsername());
        return response;
    }
}
