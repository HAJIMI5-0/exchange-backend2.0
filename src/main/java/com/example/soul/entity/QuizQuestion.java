package com.example.soul.entity;

public class QuizQuestion {

    private String skill;
    private String difficulty;
    private String question;
    private String answer;

    public QuizQuestion() {
    }

    public QuizQuestion(String skill, String difficulty, String question, String answer) {
        this.skill = skill;
        this.difficulty = difficulty;
        this.question = question;
        this.answer = answer;
    }

    public String getSkill() {
        return skill;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}