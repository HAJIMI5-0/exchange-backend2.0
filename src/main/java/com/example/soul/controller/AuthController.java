package com.example.soul.controller;

import com.example.soul.entity.User;
import com.example.soul.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class AuthController {

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    //登录接口
    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Map<String, String> body) {
        String username = body.get("username");
        String password = body.get("password");

        log.info("POST /api/login called, username={}", username);

        Map<String, Object> result = new HashMap<>();
        User user = userService.findByUsername(username).orElse(null);

        if (user != null && password != null && password.equals(user.getPassword())) {
            result.put("success", true);
            result.put("username", user.getUsername());
            result.put("message", "Login successful");
            log.info("Login success for username={}", username);
        } else {
            result.put("success", false);
            result.put("message", "Invalid username or password");
            log.warn("Login failed for username={}", username);
        }

        return result;
    }
}
