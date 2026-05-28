package com.example.soul.controller;

import com.example.soul.dto.CommentRequest;
import com.example.soul.entity.Comment;
import com.example.soul.entity.User;
import com.example.soul.repository.CommentRepository;
import com.example.soul.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 댓글 API 컨트롤러
 * 评论 API 控制器
 */
@RestController

// 基础地址
// /api/comments
@RequestMapping("/api/comments")

// React 允许访问
@CrossOrigin(origins = "*")
public class CommentController {

    // 评论数据库连接
    private final CommentRepository commentRepository;

    // 用户数据库连接
    private final UserRepository userRepository;

    // 构造函数注入
    public CommentController(
            CommentRepository commentRepository,
            UserRepository userRepository
    ) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
    }

    /**
     * 查询某个帖子的全部评论
     *
     * GET /api/comments/1
     */
    @GetMapping("/{boardId}")
    public List<Comment> getComments(
            @PathVariable Long boardId
    ) {

        // 查询评论
        List<Comment> comments =
                commentRepository
                        .findByBoardIdOrderByCreatedAtAsc(boardId);

        // 给每条评论补充用户信息
        for (Comment comment : comments) {

            User user =
                    userRepository
                            .findByUsername(comment.getUsername())
                            .orElse(null);

            if (user != null) {

                // 显示名称
                comment.setName(user.getName());

                // 用户头像
                comment.setAvatar(user.getAvatar());
            }
        }

        return comments;
    }

    /**
     * 创建评论
     *
     * POST /api/comments/1
     */
    @PostMapping("/{boardId}")
    public Comment createComment(

            // 帖子ID
            @PathVariable Long boardId,

            // 前端评论数据
            @RequestBody CommentRequest request
    ) {

        // 创建评论对象
        Comment comment = new Comment();

        // 设置帖子ID
        comment.setBoardId(boardId);

        // 设置用户名（登录账号）
        comment.setUsername(request.getUsername());

        // 设置显示名称
        comment.setAuthor(request.getAuthor());

        // 设置评论内容
        comment.setContent(request.getContent());

        // 设置评论时间
        comment.setCreatedAt(LocalDateTime.now());

        // 保存数据库
        Comment savedComment =
                commentRepository.save(comment);

        // 查询用户信息
        User user =
                userRepository
                        .findByUsername(savedComment.getUsername())
                        .orElse(null);

        // 返回 name + avatar
        if (user != null) {

            savedComment.setName(user.getName());

            savedComment.setAvatar(user.getAvatar());
        }

        return savedComment;
    }

    /**
     * 删除评论
     *
     * DELETE /api/comments/1?username=gxc
     */
    @DeleteMapping("/{commentId}")
    public ResponseEntity<?> deleteComment(

            @PathVariable Long commentId,

            @RequestParam String username
    ) {

        // 查询评论
        Comment comment =
                commentRepository.findById(commentId)
                        .orElse(null);

        // 评论不存在
        if (comment == null) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(message("댓글이 존재하지 않습니다."));
        }

        // 查询用户
        User user =
                userRepository.findByUsername(username)
                        .orElse(null);

        // 用户不存在
        if (user == null) {

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(message("사용자가 존재하지 않습니다."));
        }

        // 是否本人
        boolean isAuthor =
                username.equals(comment.getUsername());

        // 是否管理员
        boolean isAdmin =
                "ADMIN".equals(user.getRole());

        // 不是本人也不是管理员
        if (!isAuthor && !isAdmin) {

            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(message("삭제 권한이 없습니다."));
        }

        // 删除评论
        commentRepository.delete(comment);

        return ResponseEntity.ok(
                message("댓글 삭제 완료")
        );
    }

    /**
     * 返回消息
     */
    private Map<String, Object> message(String msg) {

        Map<String, Object> map =
                new LinkedHashMap<>();

        map.put("message", msg);

        return map;
    }
}