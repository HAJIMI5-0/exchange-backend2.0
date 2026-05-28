package com.example.soul.controller;

import com.example.soul.entity.Board;
import com.example.soul.service.BoardService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/board")
@CrossOrigin(
        origins = "*",
        allowedHeaders = "*",
        methods = {
                RequestMethod.GET,
                RequestMethod.POST,
                RequestMethod.PUT,
                RequestMethod.DELETE,
                RequestMethod.OPTIONS
        }
)
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    // =========================
    // 전체 게시글 조회
    // =========================
    @GetMapping
    public List<Board> getBoards() {
        return boardService.getBoards();
    }

    // =========================
    // 게시글 작성 (✔ 修复增强版)
    // =========================
    @PostMapping
    public Board createBoard(@RequestBody Board board) {

        // ✔ 必须字段校验（防止空数据）
        if (board.getTitle() == null || board.getTitle().trim().isEmpty()) {
            throw new RuntimeException("title 필요");
        }

        if (board.getContent() == null || board.getContent().trim().isEmpty()) {
            throw new RuntimeException("content 필요");
        }

        if (board.getUsername() == null || board.getUsername().trim().isEmpty()) {
            throw new RuntimeException("username 필요");
        }

        if (board.getDate() == null || board.getDate().isEmpty()) {
            board.setDate(LocalDate.now().toString());
        }

        return boardService.saveBoard(board);
    }

    // =========================
    // 게시글 상세 조회
    // =========================
    @GetMapping("/{id}")
    public Board getBoard(@PathVariable Long id) {
        return boardService.getBoard(id);
    }

    // =========================
    // 카테고리 조회
    // =========================
    @GetMapping("/category/{category}")
    public List<Board> getCategoryBoards(@PathVariable String category) {
        return boardService.getByCategory(category);
    }

    // =========================
    // 검색
    // =========================
    @GetMapping("/search")
    public List<Board> searchBoards(@RequestParam String keyword) {
        return boardService.search(keyword);
    }

    // =========================
    // 삭제（username + ADMIN）
    // =========================
    @DeleteMapping("/{id}")
    public void deleteBoard(
            @PathVariable Long id,
            @RequestParam String username
    ) {
        boardService.deleteBoard(id, username);
    }
}