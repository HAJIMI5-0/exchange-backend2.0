package com.example.soul.controller;

import com.example.soul.entity.User;
import com.example.soul.service.RecommendationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recommend")
public class    RecommendationController {

    private final RecommendationService recommendationService;

    public RecommendationController(RecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }

    //匹配的人类
    @GetMapping("/partners/{userId}")
    public List<User> recommendPartners(@PathVariable Long userId) {
        return recommendationService.recommendPartners(userId);
    }

    //技能
    @GetMapping("/skills")
    public List<String> recommendSkills() {
        return recommendationService.recommendSkills();
    }
}