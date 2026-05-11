package com.example.soul.service;

import com.example.soul.entity.MatchResult;
import com.example.soul.entity.User;
import com.example.soul.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MatchService {

    private final UserRepository userRepository;
    private final Map<String, List<String>> similarSkills = new HashMap<>();

    public MatchService(UserRepository userRepository) {
        this.userRepository = userRepository;
        initSkillMap();
    }

    private void initSkillMap() {
        similarSkills.put("java", Arrays.asList("kotlin", "spring", "backend"));
        similarSkills.put("python", Arrays.asList("data science", "ai", "machine learning"));
        similarSkills.put("english", Arrays.asList("korean", "japanese", "communication"));
        similarSkills.put("korean", Arrays.asList("english", "japanese", "language"));
        similarSkills.put("javascript", Arrays.asList("typescript", "react", "frontend"));
        similarSkills.put("react", Arrays.asList("vue", "frontend", "javascript"));
    }

    public List<MatchResult> findMatches(Long userId) {
        User currentUser = userRepository.findById(userId).orElse(null);

        if (currentUser == null) {
            return new ArrayList<>();
        }

        List<User> allUsers = userRepository.findAll();
        List<MatchResult> results = new ArrayList<>();

        for (User user : allUsers) {
            if (user.getId().equals(userId)) {
                continue;
            }

            int score1 = calculateSkillScore(currentUser.getSkillWant(), user.getSkillOffer());
            int score2 = calculateSkillScore(currentUser.getSkillOffer(), user.getSkillWant());

            int totalScore = (score1 + score2) / 2;

            String reason = buildReason(currentUser, user, score1, score2, totalScore);

            if (totalScore >= 50) {
                results.add(new MatchResult(
                        user.getId(),
                        user.getName(),
                        user.getEmail(),
                        user.getSkillOffer(),
                        user.getSkillWant(),
                        totalScore,
                        reason
                ));
            }
        }

        results.sort((a, b) -> Integer.compare(b.getScore(), a.getScore()));
        return results;
    }

    private int calculateSkillScore(String targetSkill, String candidateSkill) {
        if (targetSkill == null || candidateSkill == null) {
            return 0;
        }

        String target = targetSkill.trim().toLowerCase();
        String candidate = candidateSkill.trim().toLowerCase();

        if (target.equals(candidate)) {
            return 100;
        }

        List<String> related = similarSkills.get(target);
        if (related != null && related.contains(candidate)) {
            return 80;
        }

        if (target.contains(candidate) || candidate.contains(target)) {
            return 70;
        }

        return 0;
    }

    private String buildReason(User currentUser, User matchedUser, int score1, int score2, int totalScore) {
        String learnReason = explainRelation(currentUser.getSkillWant(), matchedUser.getSkillOffer(), score1);
        String teachReason = explainRelation(currentUser.getSkillOffer(), matchedUser.getSkillWant(), score2);

        return "学习匹配: " + learnReason +
                "；交换匹配: " + teachReason +
                "；综合分数: " + totalScore;
    }

    private String explainRelation(String skillA, String skillB, int score) {
        if (skillA == null || skillB == null) {
            return "技能信息不完整";
        }

        if (score == 100) {
            return skillA + " 与 " + skillB + " 完全匹配";
        }

        if (score >= 80) {
            return skillA + " 与 " + skillB + " 属于相似技能领域";
        }

        if (score >= 70) {
            return skillA + " 与 " + skillB + " 存在部分包含关系";
        }

        return skillA + " 与 " + skillB + " 相关性较低";
    }
}