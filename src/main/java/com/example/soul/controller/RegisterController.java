package com.example.soul.controller;

import com.example.soul.dto.RegisterRequest;
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
public class RegisterController {

    private static final Logger log = LoggerFactory.getLogger(RegisterController.class);

    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    //注册接口
    @PostMapping("/register")
    public Map<String, Object> register(@RequestBody RegisterRequest request) {
        Map<String, Object> result = new HashMap<>();

        String username = normalizeRequired(request.getUsername());
        String password = normalizeRequired(request.getPassword());
        String phone = normalizeNullable(request.getPhone());
        String email = normalizeNullable(request.getEmail());

        if (username == null || password == null) {
            result.put("success", false);
            result.put("message", "Username and password are required");
            return result;
        }

        if (userService.existsByUsername(username)) {
            result.put("success", false);
            result.put("message", "Username already exists");
            return result;
        }

        if (phone != null && userService.existsByPhone(phone)) {
            result.put("success", false);
            result.put("message", "Phone already exists");
            return result;
        }

        if (email != null && userService.existsByEmail(email)) {
            result.put("success", false);
            result.put("message", "Email already exists");
            return result;
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setPhone(phone);
        user.setEmail(email);
        user.setName(username);

        userService.save(user);

        result.put("success", true);
        result.put("username", user.getUsername());
        result.put("message", "Register successful");
        log.info("Register success for username={}", user.getUsername());
        return result;
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }

    private String normalizeRequired(String value) {
        if (isBlank(value)) {
            return null;
        }
        return value.trim();
    }

    private String normalizeNullable(String value) {
        if (isBlank(value)) {
            return null;
        }
        return value.trim();
    }
}
