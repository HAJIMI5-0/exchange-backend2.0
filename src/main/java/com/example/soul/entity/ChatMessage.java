package com.example.soul.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity // 实体类，对应数据库表
@Table(name = "chat_message") // 表名：chat_message
public class ChatMessage {

    @Id // 主键
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 自动增长
    private Long id;

    @Column(name = "sender_username", nullable = false) // 发送者
    private String senderUsername;

    @Column(name = "receiver_username", nullable = false) // 接收者
    private String receiverUsername;

    @Column(name = "content", columnDefinition = "TEXT", nullable = false) // 消息内容
    private String content;

    @Column(name = "created_at") // 创建时间
    private LocalDateTime createdAt;



    // 发送者类型（USER / AI / SYSTEM）
    @Column(name = "sender_type")
    private String senderType;

    @PrePersist // 保存前自动执行
    public void prePersist() {
        this.createdAt = LocalDateTime.now(); // 设置当前时间
    }

    public ChatMessage() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setSenderUsername(String senderUsername) {
        this.senderUsername = senderUsername;
    }

    public void setReceiverUsername(String receiverUsername) {
        this.receiverUsername = receiverUsername;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }


    public void setSenderType(String senderType) {
        this.senderType = senderType;
    }
}