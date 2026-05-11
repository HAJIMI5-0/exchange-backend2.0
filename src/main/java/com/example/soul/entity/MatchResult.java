package com.example.soul.entity;

public class MatchResult {

    private Long userId;
    private String name;
    private String email;
    private String skillOffer;
    private String skillWant;
    private int score;
    private String reason;

    public MatchResult() {}

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