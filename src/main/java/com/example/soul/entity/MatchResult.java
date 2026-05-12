package com.example.soul.entity;

// 匹配结果数据结构（用于返回匹配系统计算结果）
// 매칭 결과 데이터 구조 (매칭 시스템 계산 결과 반환용)
public class MatchResult {

    // 用户ID
    // 사용자 ID
    private Long userId;

    // 用户姓名
    // 사용자 이름
    private String name;

    // 用户邮箱
    // 사용자 이메일
    private String email;

    // 用户可提供的技能
    // 사용자가 제공 가능한 기술
    private String skillOffer;

    // 用户想学习的技能
    // 사용자가 배우고 싶은 기술
    private String skillWant;

    // 匹配分数（算法计算结果）
    // 매칭 점수 (알고리즘 계산 결과)
    private int score;

    // 匹配原因说明
    // 매칭 이유 설명
    private String reason;

    // 默认构造函数
    // 기본 생성자
    public MatchResult() {}

    // 全参构造函数
    // 전체 필드 생성자
    public MatchResult(Long userId, String name, String email,
                       String skillOffer, String skillWant,
                       int score, String reason) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.skillOffer = skillOffer;
        this.skillWant = skillWant;
        this.score = score;
        this.reason = reason;
    }

    // ===== Getter / Setter =====

    public Long getUserId() { return userId; }

    public String getName() { return name; }

    public String getEmail() { return email; }

    public String getSkillOffer() { return skillOffer; }

    public String getSkillWant() { return skillWant; }

    public int getScore() { return score; }

    public String getReason() { return reason; }

    public void setUserId(Long userId) { this.userId = userId; }

    public void setName(String name) { this.name = name; }

    public void setEmail(String email) { this.email = email; }

    public void setSkillOffer(String skillOffer) { this.skillOffer = skillOffer; }

    public void setSkillWant(String skillWant) { this.skillWant = skillWant; }

    public void setScore(int score) { this.score = score; }

    public void setReason(String reason) { this.reason = reason; }
}