package com.example.soul.entity;

import jakarta.persistence.*;

@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String category;

    private String title;

    // ✔ 统一字段：发帖人
    private String username;

    @Column(columnDefinition = "TEXT")
    private String content;

    private int views;

    private String date;

    // =========================
// 用户显示名称（不存数据库）
// =========================
    @Transient
    private String name;

    // =========================
// 用户头像（不存数据库）
// =========================
    @Transient  //临时存储不写入数据库
    private String avatar;

    public Board() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // ❌ author 已废弃
    // public String getAuthor() { ... }
    // public void setAuthor(...) { ... }

    // ✔ 新统一字段
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
