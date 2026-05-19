package com.example.soul.entity;

import jakarta.persistence.*;

@Entity // 实体类
@Table(name = "quiz_question") // 数据库表名
public class QuizQuestion {

    @Id // 主键
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 自动增长
    private Long id;

    // QuizResult ID（关联 QuizResult 表）
    @Column(name = "quiz_result_id", nullable = false)
    private Long quizResultId;

    // 题目内容
    @Column(name = "question_text", columnDefinition = "TEXT")
    private String questionText;

    // 选项 JSON
    @Column(name = "options_json", columnDefinition = "TEXT")
    private String optionsJson;

    // 正确答案
    @Column(name = "correct_answer")
    private String correctAnswer;

    // 用户答案
    @Column(name = "user_answer")
    private String userAnswer;

    // 是否正确
    @Column(name = "is_correct")
    private Boolean isCorrect;

    // 难度等级
    @Column(name = "difficulty_level")
    private String difficultyLevel;

    // AI解析
    @Column(name = "explanation", columnDefinition = "TEXT")
    private String explanation;

    public QuizQuestion() {
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

    public Long getQuizResultId() {
        return quizResultId;
    }

    public void setQuizResultId(Long quizResultId) {
        this.quizResultId = quizResultId;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getOptionsJson() {
        return optionsJson;
    }

    public void setOptionsJson(String optionsJson) {
        this.optionsJson = optionsJson;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
    }

    public Boolean getIsCorrect() {
        return isCorrect;
    }

    public void setIsCorrect(Boolean isCorrect) {
        this.isCorrect = isCorrect;
    }

    public String getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(String difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }
}