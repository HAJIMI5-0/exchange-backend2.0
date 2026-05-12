package com.example.soul.dto;

import java.util.List;

public class MatchUserResponse {

    private Long id;
    private String username;       // 用户账号，申请好友时必须用
    private String name;           // 页面显示名字
    private Integer age;
    private String gender;         // 性别
    private String nationality;    // 国籍
    private String avatar;         // 头像
    private List<String> skills;   // 擅长技能
    private List<String> wants;    // 想学技能

    public MatchUserResponse() {
    }

    public MatchUserResponse(
            Long id,
            String username,
            String name,
            Integer age,
            String gender,
            String nationality,
            String avatar,
            List<String> skills,
            List<String> wants
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
    }

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
}