package com.example.soul.dto;

public class MemberProfileResponse {

    private Long id;
    private String username;
    private String phone;
    private String email;
    private String address;
    private String avatar;
    private String gender;
    private Integer age;
    private String teachSkill;
    private String learnSkill;

    public MemberProfileResponse() {
    }

    public MemberProfileResponse(
            Long id,
            String username,
            String phone,
            String email,
            String address,
            String avatar,
            String gender,
            Integer age,
            String teachSkill,
            String learnSkill
    ) {
        this.id = id;
        this.username = username;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.avatar = avatar;
        this.gender = gender;
        this.age = age;
        this.teachSkill = teachSkill;
        this.learnSkill = learnSkill;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getTeachSkill() {
        return teachSkill;
    }

    public void setTeachSkill(String teachSkill) {
        this.teachSkill = teachSkill;
    }

    public String getLearnSkill() {
        return learnSkill;
    }

    public void setLearnSkill(String learnSkill) {
        this.learnSkill = learnSkill;
    }
}

