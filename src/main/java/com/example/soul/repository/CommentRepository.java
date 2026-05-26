package com.example.soul.repository;

import com.example.soul.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 댓글 Repository
 * 评论数据库接口
 */
public interface CommentRepository extends JpaRepository<Comment, Long> {

    /**
     * 根据帖子ID查询评论，按评论时间正序排列
     */
    List<Comment> findByBoardIdOrderByCreatedAtAsc(Long boardId);
}