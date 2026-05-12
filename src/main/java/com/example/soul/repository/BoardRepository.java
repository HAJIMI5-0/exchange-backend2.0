package com.example.soul.repository;

import com.example.soul.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// 게시판 데이터베이스 접근 인터페이스
// 告示板数据库访问接口

// JpaRepository를 상속받아 기본 CRUD 기능 자동 생성
// 继承 JpaRepository 后自动生成 기본 CRUD 功能
public interface BoardRepository
        extends JpaRepository<Board, Long> {

    // 카테고리별 게시글 조회
    // 根据分类查询帖子
    List<Board> findByCategory(String category);

    // 제목 포함 검색
    // 根据标题关键字搜索帖子
    List<Board> findByTitleContaining(String keyword);
}