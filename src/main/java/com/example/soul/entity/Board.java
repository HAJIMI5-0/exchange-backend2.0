package com.example.soul.entity;

import jakarta.persistence.*;

// 게시글 엔티티 클래스
// 告示板帖子实体类

// 데이터베이스의 board 테이블과 연결
// 对应数据库中的 board 表
@Entity
public class Board {

    // 게시글 번호 (기본키)
    // 帖子编号（主键）
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 게시글 카테고리
    // 帖子分类

    // 예: 자유게시판 / 질문게시판 / 자료공유
    // 例如：自由게시판 / 提问게시판 / 资料共享
    private String category;

    // 게시글 제목
    // 帖子标题
    private String title;

    // 작성자 이름
    // 作者名字
    private String author;

    // 게시글 내용
    // 帖子内容

    // TEXT 타입으로 긴 문자열 저장 가능
    // TEXT 类型，可保存长文本
    @Column(columnDefinition = "TEXT")
    private String content;

    // 조회수
    // 浏览量
    private int views;

    // 작성 날짜
    // 发布日期
    private String date;

    // 기본 생성자
    // 默认构造函数
    public Board() {
    }

    // 게시글 번호 반환
    // 返回帖子编号
    public Long getId() {
        return id;
    }

    // 카테고리 반환
    // 返回分类
    public String getCategory() {
        return category;
    }

    // 카테고리 설정
    // 设置分类
    public void setCategory(String category) {
        this.category = category;
    }

    // 제목 반환
    // 返回标题
    public String getTitle() {
        return title;
    }

    // 제목 설정
    // 设置标题
    public void setTitle(String title) {
        this.title = title;
    }

    // 작성자 반환
    // 返回作者
    public String getAuthor() {
        return author;
    }

    // 작성자 설정
    // 设置作者
    public void setAuthor(String author) {
        this.author = author;
    }

    // 내용 반환
    // 返回内容
    public String getContent() {
        return content;
    }

    // 내용 설정
    // 设置内容
    public void setContent(String content) {
        this.content = content;
    }

    // 조회수 반환
    // 返回浏览量
    public int getViews() {
        return views;
    }

    // 조회수 설정
    // 设置浏览量
    public void setViews(int views) {
        this.views = views;
    }

    // 날짜 반환
    // 返回日期
    public String getDate() {
        return date;
    }

    // 날짜 설정
    // 设置日期
    public void setDate(String date) {
        this.date = date;
    }
}