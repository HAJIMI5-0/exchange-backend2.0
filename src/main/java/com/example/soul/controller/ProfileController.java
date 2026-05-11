package com.example.soul.controller;

import com.example.soul.dto.ProfileResponse;
import com.example.soul.dto.ProfileUpdateRequest;
import com.example.soul.entity.User;
import com.example.soul.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;
/**
 * 用户个人资料接口
 * 提供用户信息查询与修改功能
 */
@RestController
@RequestMapping("/api")
public class ProfileController {
    //用户资料接口
    //查询
    private final UserRepository userRepository;

    public ProfileController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    /**
     * 查询用户个人资料
     *
     * @param username 用户名
     * @return 用户资料信息（包含基本信息、技能等）
     */
    @GetMapping("/profile")
    public ResponseEntity<?> getProfile(@RequestParam String username) {
        return userRepository.findByUsername(username)
                .<ResponseEntity<?>>map(user -> ResponseEntity.ok(toProfile(user)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(message("User not found")));
    }
    /**
     * 更新用户个人资料
     *
     * @param request 前端传入的用户资料（手机号、邮箱、技能等）
     * @return 更新后的用户资料
     */
    @PutMapping("/profile")
    public ResponseEntity<?> updateProfile(@RequestBody ProfileUpdateRequest request) {
        String username = request.getUsername();
        if (username == null || username.trim().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message("username is required"));
        }

        User user = userRepository.findByUsername(username.trim()).orElse(null);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message("User not found"));
        }

        user.setPhone(trimToNull(request.getPhone()));
        user.setEmail(trimToNull(request.getEmail()));
        user.setAddress(trimToNull(request.getAddress()));
        user.setAvatar(trimToNull(request.getAvatar()));
        user.setGender(trimToNull(request.getGender()));
        user.setAge(parseNullableInt(request.getAge()));

        // Frontend uses teachSkill/learnSkill; backend entity stores them as skillOffer/skillWant.
        user.setSkillOffer(trimToNull(request.getTeachSkill()));
        user.setSkillWant(trimToNull(request.getLearnSkill()));

        User saved = userRepository.save(user);
        return ResponseEntity.ok(toProfile(saved));
    }

    private ProfileResponse toProfile(User user) {
        return new ProfileResponse(
                user.getUsername(),
                user.getPhone(),
                user.getEmail(),
                user.getAddress(),
                user.getAvatar(),
                user.getGender(),
                user.getAge(),
                user.getSkillOffer(),
                user.getSkillWant()
        );
    }

    private Map<String, Object> message(String msg) {
        Map<String, Object> res = new LinkedHashMap<>();
        res.put("message", msg);
        return res;
    }

    private String trimToNull(String value) {
        if (value == null) {
            return null;
        }
        String trimmed = value.trim();
        return trimmed.isEmpty() ? null : trimmed;
    }

    private Integer parseNullableInt(String value) {
        String trimmed = trimToNull(value);
        if (trimmed == null) {
            return null;
        }
        try {
            return Integer.parseInt(trimmed);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
