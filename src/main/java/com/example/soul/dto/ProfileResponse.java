package com.example.soul.dto;

// 用户个人资料响应 DTO
// 사용자 개인 프로필 응답 DTO
public class ProfileResponse {

    // 用户账号（登录名）
    // 사용자 계정 (로그인 아이디)
    private String username;

    // 用户昵称/姓名
    // 사용자 이름 / 닉네임
    private String name;

    // 电话号码
    // 전화번호
    private String phone;

    // 邮箱
    // 이메일
    private String email;

    // 地址
    // 주소
    private String address;

    // 头像URL
    // 프로필 이미지 URL
    private String avatar;

    // 性别
    // 성별
    private String gender;

    // 年龄
    // 나이
    private Integer age;

    // 我可以教授的技能
    // 내가 가르칠 수 있는 기술
    private String teachSkill;

    // 我想学习的技能
    // 내가 배우고 싶은 기술
    private String learnSkill;

    // 国籍
    // 국적
    private String nationality;

    // ===== 默认构造函数 =====
    // 기본 생성자
    public ProfileResponse() {
    }

    // ===== 全参构造函数 =====
    // 전체 필드 생성자
    public ProfileResponse(
            String username,
            String name,
            String phone,
            String email,
            String address,
            String avatar,
            String gender,
            Integer age,
            String teachSkill,
            String nationality,
            String learnSkill
    ) {
        this.username = username;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.avatar = avatar;
        this.gender = gender;
        this.age = age;
        this.teachSkill = teachSkill;
        this.learnSkill = learnSkill;
        this.nationality = nationality;
    }

    // ===== Getter / Setter =====

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

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }
}