package com.example.soul.controller;

import com.example.soul.dto.MemberProfileResponse;
import com.example.soul.dto.MemberProfileUpdateRequest;
import com.example.soul.entity.User;
import com.example.soul.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    private final UserRepository userRepository;

    public MemberController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getMember(@PathVariable Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message("User not found"));
        }
        return ResponseEntity.ok(toMember(user));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<?> updateMember(@PathVariable Long userId, @RequestBody MemberProfileUpdateRequest request) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message("User not found"));
        }

        user.setPhone(trimToNull(request.getPhone()));
        user.setEmail(trimToNull(request.getEmail()));
        user.setAddress(trimToNull(request.getAddress()));
        user.setAvatar(trimToNull(request.getAvatar()));
        user.setGender(trimToNull(request.getGender()));
        user.setAge(parseNullableInt(request.getAge()));
        user.setSkillOffer(trimToNull(request.getTeachSkill()));
        user.setSkillWant(trimToNull(request.getLearnSkill()));
        user.setNationality(trimToNull(request.getNationality()));

        User saved = userRepository.save(user);
        return ResponseEntity.ok(toMember(saved));
    }

    private MemberProfileResponse toMember(User user) {
        return new MemberProfileResponse(
                user.getId(),
                user.getUsername(),
                user.getPhone(),
                user.getEmail(),
                user.getAddress(),
                user.getAvatar(),
                user.getGender(),
                user.getAge(),
                user.getSkillOffer(),
                user.getSkillWant(),
                user.getNationality()
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

