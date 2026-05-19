package com.example.soul.dto;

import java.util.List;

public class MatchUserResponse {

    // 用户ID
    private Long id;

    // 用户账号（申请好友、创建聊天室时使用）
    private String username;

    // 页面显示名字
    private String name;

    // 年龄
    private Integer age;

    // 性别
    private String gender;

    // 国籍
    private String nationality;

    // 头像
    private String avatar;

    // 擅长技能
    private List<String> skills;

    // 想学技能
    private List<String> wants;

    // 学习时间段
    private String timeSlot;

    // 学习等级
    private String learnLevel;

    // ===== 默认构造函数 =====
    public MatchUserResponse() {
    }

    // ===== 全参构造函数 =====
    public MatchUserResponse(
            Long id,
            String username,
            String name,
            Integer age,
            String gender,
            String nationality,
            String avatar,
            List<String> skills,
            List<String> wants,
            String timeSlot,
            String learnLevel
    ) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.nationality = nationality;
        this.avatar = avatar;
        this.skills = skills;
        this.wants = wants;
        this.timeSlot = timeSlot;
        this.learnLevel = learnLevel;
    }

    // ===== Getter / Setter =====

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public List<String> getWants() {
        return wants;
    }

    public void setWants(List<String> wants) {
        this.wants = wants;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }

    public String getLearnLevel() {
        return learnLevel;
    }

    public void setLearnLevel(String learnLevel) {
        this.learnLevel = learnLevel;
    }
}