package com.example.soul.service;

import com.example.soul.entity.User;
import com.example.soul.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RecommendationService {

    private final UserRepository userRepository;

    public RecommendationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> recommendPartners(Long userId) {
        User currentUser = userRepository.findById(userId).orElse(null);
        if (currentUser == null) {
            return new ArrayList<>();
        }

        List<User> allUsers = userRepository.findAll();
        List<User> result = new ArrayList<>();

        for (User user : allUsers) {
            if (!user.getId().equals(userId)) {
                if (user.getSkillOffer() != null &&
                        currentUser.getSkillWant() != null &&
                        user.getSkillOffer().equalsIgnoreCase(currentUser.getSkillWant())) {
                    result.add(user);
                }
            }
        }
        return result;
    }

    public List<String> recommendSkills() {
        List<User> allUsers = userRepository.findAll();
        Map<String, Integer> countMap = new HashMap<>();

        for (User user : allUsers) {
            if (user.getSkillOffer() != null) {
                String skill = user.getSkillOffer().trim();
                countMap.put(skill, countMap.getOrDefault(skill, 0) + 1);
            }
        }

        List<Map.Entry<String, Integer>> sorted = new ArrayList<>(countMap.entrySet());
        sorted.sort((a, b) -> b.getValue() - a.getValue());

        List<String> result = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : sorted) {
            result.add(entry.getKey());
        }

        return result;
    }
}