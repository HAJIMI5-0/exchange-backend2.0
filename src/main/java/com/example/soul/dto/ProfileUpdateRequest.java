package com.example.soul.dto;

// 用户个人资料修改请求 DTO
// 사용자 개인 프로필 수정 요청 DTO
public class ProfileUpdateRequest {

    // 用户账号（登录名）
    // 사용자 계정 (로그인 아이디)
    private String username;

    // 用户姓名/昵称
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

    // 年龄（字符串接收，后端可转换）
    // 나이 (문자열로 전달, 서버에서 변환 가능)
    private String age;

    // 我可以教授的技能
    // 내가 가르칠 수 있는 기술
    private String teachSkill;

    // 我想学习的技能
    // 내가 배우고 싶은 기술
    private String learnSkill;

    // 国籍
    // 국적
    private String nationality;

    // 学习时间段
    // 학습 가능 시간대
    private String timeSlot;

    // 想学习的等级
    // 배우고 싶은 기술 레벨
    private String learnLevel;

    // 项目 / 奖项 / 证书
    // 프로젝트 / 수상 / 자격증
    private String projectAwards;

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

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
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

    public String getProjectAwards() {
        return projectAwards;
    }

    public void setProjectAwards(String projectAwards) {
        this.projectAwards = projectAwards;
    }
}