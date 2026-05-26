package com.example.soul.dto;

/**
 * 댓글 작성 요청 DTO
 * 评论创建请求 DTO
 */
public class CommentRequest {

    // 评论作者
    private String author;

    // 评论内容
    private String content;

    public CommentRequest() {
    }

    // ===== Getter / Setter =====

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}