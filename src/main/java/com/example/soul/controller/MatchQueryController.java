package com.example.soul.controller;

import com.example.soul.dto.MatchUserResponse;
import com.example.soul.entity.User;
import com.example.soul.repository.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * This is a DB-backed endpoint that returns the same basic shape as the embedded mockUsers in the frontend.
 * The current frontend Match.jsx does not call the backend yet, but this endpoint is ready for wiring later.
 */
@RestController
@RequestMapping("/api/match")
public class MatchQueryController {

    private final UserRepository userRepository;

    public MatchQueryController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<MatchUserResponse> match(
            @RequestParam(required = false) String haveSkill,
            @RequestParam(required = false) String wantSkill,
            @RequestParam(defaultValue = "5") int limit
    ) {
        String have = normalize(haveSkill);
        String want = normalize(wantSkill);

        List<User> all = userRepository.findAll();
        List<User> filtered = new ArrayList<>();
        for (User u : all) {
            boolean ok = true;
            if (have != null) {
                ok = ok && containsSkill(u.getSkillWant(), have);
            }
            if (want != null) {
                ok = ok && containsSkill(u.getSkillOffer(), want);
            }
            if (ok) {
                filtered.add(u);
            }
        }

        Collections.shuffle(filtered);
        if (limit < 0) {
            limit = 0;
        }

        return filtered.stream()
                .limit(limit)
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    private MatchUserResponse toResponse(User u) {
        String name = u.getName();
        if (name == null || name.trim().isEmpty()) {
            name = u.getUsername();
        }
        List<String> skills = splitSkills(u.getSkillOffer());
        List<String> wants = splitSkills(u.getSkillWant());
        return new MatchUserResponse(u.getId(), name, u.getAge(), skills, wants);
    }

    private List<String> splitSkills(String raw) {
        String v = normalize(raw);
        if (v == null) {
            return List.of();
        }
        // Allow comma-separated lists (e.g. "Java, Spring, MySQL") or single values.
        String[] parts = v.split(",");
        List<String> res = new ArrayList<>();
        for (String p : parts) {
            String t = normalize(p);
            if (t != null) {
                res.add(t);
            }
        }
        return res;
    }

    private boolean containsSkill(String raw, String target) {
        if (raw == null) {
            return false;
        }
        String hay = raw.toLowerCase(Locale.ROOT);
        String needle = target.toLowerCase(Locale.ROOT);
        // Simple match: exact equals (trimmed) or contains in comma-separated list.
        for (String part : hay.split(",")) {
            String t = part.trim();
            if (!t.isEmpty() && t.equals(needle)) {
                return true;
            }
        }
        return hay.contains(needle);
    }

    private String normalize(String v) {
        if (v == null) {
            return null;
        }
        String t = v.trim();
        return t.isEmpty() ? null : t;
    }
}

