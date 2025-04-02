package com.chenminty.campus3d.controller;

import com.chenminty.campus3d.dto.request.LoginRequest;
import com.chenminty.campus3d.dto.request.RegisterRequest;
import com.chenminty.campus3d.dto.response.LoginResponse;
import com.chenminty.campus3d.dto.response.Response;
import com.chenminty.campus3d.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {
    private final UserService userService;

    @PostMapping("/register")
    public Response<?> register(@RequestBody RegisterRequest request) {
        return userService.register(request) ?
                Response.success("注册成功") :
                Response.error(-1, "注册失败");
    }

    @PostMapping("/login")
    public Response<?> login(@RequestBody LoginRequest request) {
        LoginResponse response = userService.login(request);
        return Response.success(response);
    }

    @GetMapping("/test")
    public Response<?> test() {
        return Response.success("如果能看到这条信息，说明通过了JWT验证");
    }
}
