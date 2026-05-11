package com.example.soul.dto;

import java.util.List;

public class MatchUserResponse {

    private Long id;
    private String name;
    private Integer age;
    private List<String> skills;
    private List<String> wants;

    public MatchUserResponse() {
    }

    public MatchUserResponse(Long id, String name, Integer age, List<String> skills, List<String> wants) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.skills = skills;
        this.wants = wants;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

