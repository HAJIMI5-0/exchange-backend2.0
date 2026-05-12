package com.example.soul.service;

import com.example.soul.entity.MatchResult;
import com.example.soul.entity.User;
import com.example.soul.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MatchService {

    private final UserRepository userRepository;

    public MatchService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<MatchResult> findMatches(Long userId) {

        User currentUser = userRepository.findById(userId).orElse(null);

        if (currentUser == null) {
            return new ArrayList<>();
        }

        List<User> allUsers = userRepository.findAll();
        List<MatchResult> results = new ArrayList<>();

        for (User user : allUsers) {

            // 排除自己
            if (user.getId().equals(userId)) {
                continue;
            }

            // 简单匹配逻辑：技能字段有交集或相等
            boolean match =
                    isMatch(currentUser.getSkillWant(), user.getSkillOffer()) ||
                            isMatch(currentUser.getSkillOffer(), user.getSkillWant());

            if (match) {
                results.add(new MatchResult(
                        user.getId(),
                        user.getName(),
                        user.getEmail(),
                        user.getSkillOffer(),
                        user.getSkillWant(),
                        0,          // score 不再使用
                        null        // reason 不再使用
                ));
            }
        }

        return results;
    }

    private boolean isMatch(String a, String b) {
        if (a == null || b == null) {
            return false;
        }
        return a.equalsIgnoreCase(b)
                || a.toLowerCase().contains(b.toLowerCase())
                || b.toLowerCase().contains(a.toLowerCase());
    }
}