package com.example.soul.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String phone;
    private String name;
    private String email;
    private String address;
    private String avatar;
    private String gender;
    private Integer age;

    private String skillOffer;
    private String skillWant;

    private String nationality;


    // 新增字段

    // 擅长技能等级
    private String skillOfferLevel;

    // 想学习技能等级
    private String skillWantLevel;

    // 可交流时间
    private String availableTime;

    // 是否有项目经验
    private Boolean hasProject;

    // 项目详情
    @Column(columnDefinition = "TEXT")
    private String projectDetail;

    // 是否有获奖经历
    private Boolean hasAward;

    // 获奖详情
    @Column(columnDefinition = "TEXT")
    private String awardDetail;

    // 主题模式（light / dark）
    private String themeMode;

    // 背景风格
    private String backgroundStyle;

    // 字体大小
    private String fontSize;

    public User() {
    }

    public Long getId() {
        return id;
    }

    // 读取 修改数据
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getSkillOffer() {
        return skillOffer;
    }

    public void setSkillOffer(String skillOffer) {
        this.skillOffer = skillOffer;
    }

    public String getSkillWant() {
        return skillWant;
    }

    public void setSkillWant(String skillWant) {
        this.skillWant = skillWant;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getSkillOfferLevel() {
        return skillOfferLevel;
    }

    public void setSkillOfferLevel(String skillOfferLevel) {
        this.skillOfferLevel = skillOfferLevel;
    }

    public String getSkillWantLevel() {
        return skillWantLevel;
    }

    public void setSkillWantLevel(String skillWantLevel) {
        this.skillWantLevel = skillWantLevel;
    }

    public String getAvailableTime() {
        return availableTime;
    }

    public void setAvailableTime(String availableTime) {
        this.availableTime = availableTime;
    }

    public Boolean getHasProject() {
        return hasProject;
    }

    public void setHasProject(Boolean hasProject) {
        this.hasProject = hasProject;
    }

    public String getProjectDetail() {
        return projectDetail;
    }

    public void setProjectDetail(String projectDetail) {
        this.projectDetail = projectDetail;
    }

    public Boolean getHasAward() {
        return hasAward;
    }

    public void setHasAward(Boolean hasAward) {
        this.hasAward = hasAward;
    }

    public String getAwardDetail() {
        return awardDetail;
    }

    public void setAwardDetail(String awardDetail) {
        this.awardDetail = awardDetail;
    }

    public String getThemeMode() {
        return themeMode;
    }

    public void setThemeMode(String themeMode) {
        this.themeMode = themeMode;
    }

    public String getBackgroundStyle() {
        return backgroundStyle;
    }

    public void setBackgroundStyle(String backgroundStyle) {
        this.backgroundStyle = backgroundStyle;
    }

    public String getFontSize() {
        return fontSize;
    }

    public void setFontSize(String fontSize) {
        this.fontSize = fontSize;
    }
}
