package com.example.soul.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity // 实体类
@Table(name = "quiz_result") // 数据库表名
public class QuizResult {

    @Id // 主键
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 自动增长
    private Long id;

    // 用户ID（关联 User 表）
    @Column(name = "user_id", nullable = false)
    private Long userId;

    // 技能名称
    @Column(name = "skill_name")
    private String skillName;

    // Quiz 类型
    @Column(name = "quiz_type")
    private String quizType;

    // 等级
    @Column(name = "level")
    private String level;

    // 用户得分
    @Column(name = "score")
    private Integer score;

    // 总分
    @Column(name = "total_score")
    private Integer totalScore;

    // 题目数量
    @Column(name = "question_count")
    private Integer questionCount;

    // 来源（AI / SYSTEM / USER）
    @Column(name = "source")
    private String source;

    // 聊天室ID
    @Column(name = "chat_room_id")
    private Long chatRoomId;

    // 创建时间
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // 保存前自动设置时间
    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

    public QuizResult() {
    }

    // ===============================
    // Getter / Setter
    // ===============================

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getSkillName() {
        return skillName;
    }

    public void setSkillName(String skillName) {
        this.skillName = skillName;
    }

    public String getQuizType() {
        return quizType;
    }

    public void setQuizType(String quizType) {
        this.quizType = quizType;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Integer totalScore) {
        this.totalScore = totalScore;
    }

    public Integer getQuestionCount() {
        return questionCount;
    }

    public void setQuestionCount(Integer questionCount) {
        this.questionCount = questionCount;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Long getChatRoomId() {
        return chatRoomId;
    }

    public void setChatRoomId(Long chatRoomId) {
        this.chatRoomId = chatRoomId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}