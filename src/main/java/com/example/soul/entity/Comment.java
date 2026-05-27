package com.example.soul.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

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
    // 显示名称（昵称）
    // author = 小聪
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
     * 获取显示名称
     */
    public String getAuthor() {
        return author;
    }

    /**
     * 设置显示名称
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
}