package com.example.soul.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

/**
 * 댓글 엔티티
 * 评论实体
 */
@Entity
public class Comment {

    // =========================
    // 评论ID
    // =========================
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // =========================
    // 属于哪个帖子
    // =========================
    private Long boardId;

    // =========================
    // 登录账号（真正身份）
    // username = gxc
    // =========================
    private String username;

    // =========================
    // 显示名称（旧字段）
    // =========================
    private String author;

    // =========================
    // 评论内容
    // =========================
    @Column(columnDefinition = "TEXT")
    private String content;

    // =========================
    // 评论时间
    // =========================
    private LocalDateTime createdAt;

    // =========================
    // 用户真实名称（不存数据库）
    // =========================
    @Transient
    private String name;

    // =========================
    // 用户头像（不存数据库）
    // =========================
    @Transient
    private String avatar;

    // =========================
    // 默认构造函数
    // =========================
    public Comment() {
    }

    // =========================
    // Getter / Setter
    // =========================

    /**
     * 获取评论ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置评论ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取帖子ID
     */
    public Long getBoardId() {
        return boardId;
    }

    /**
     * 设置帖子ID
     */
    public void setBoardId(Long boardId) {
        this.boardId = boardId;
    }

    /**
     * 获取登录账号
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置登录账号
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取旧显示名称
     */
    public String getAuthor() {
        return author;
    }

    /**
     * 设置旧显示名称
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * 获取评论内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置评论内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取评论时间
     */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * 设置评论时间
     */
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * 获取真实名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置真实名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取头像
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * 设置头像
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}