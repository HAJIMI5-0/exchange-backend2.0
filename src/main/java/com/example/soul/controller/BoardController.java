package com.example.soul.controller;

import com.example.soul.entity.Board;
import com.example.soul.service.BoardService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// REST API 컨트롤러
@RestController

// 기본 주소 설정
// localhost:8080/api/board
@RequestMapping("/api/board")

// React 연결 허용
@CrossOrigin(origins = "*")
public class BoardController {

    // Service 연결
    private final BoardService boardService;

    // 생성자 주입
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    // =========================
    // 全部帖子查询接口
    // GET /api/board
    // =========================
    @GetMapping
    public List<Board> getBoards() {

        // 모든 게시글 반환
        return boardService.getBoards();
    }

    // =========================
    // 创建帖子接口
    // POST /api/board
    // =========================
    @PostMapping
    public Board createBoard(

            // React JSON 데이터 받기
            @RequestBody Board board
    ) {

        // 게시글 저장
        return boardService.saveBoard(board);
    }

    // =========================
    // 帖子详情查询接口
    //GET /api/board/1
    //点击帖子后显示详情弹窗
    // =========================
    @GetMapping("/{id}")
    public Board getBoard(

            // URL의 id 값 받기
            @PathVariable Long id
    ) {

        // 게시글 하나 조회
        return boardService.getBoard(id);
    }

    // =========================
    // 分类帖子查询接口
    // GET /api/board/category/질문게시판
    //点击页面顶部分类按钮时使用
    // =========================
    @GetMapping("/category/{category}")
    public List<Board> getCategoryBoards(

            // URL category 값 받기
            @PathVariable String category
    ) {

        // category별 조회
        return boardService.getByCategory(category);
    }

    // =========================
    // 帖子搜索接口
    // GET /api/board/search?keyword=spring
    //页面搜索框功能
    // =========================
    @GetMapping("/search")
    public List<Board> searchBoards(

            // keyword 파라미터 받기
            @RequestParam String keyword
    ) {

        // 제목 검색
        return boardService.search(keyword);
    }

    // =========================
    // 删除
    // DELETE /api/board/1
    // =========================
    @DeleteMapping("/{id}")
    public void deleteBoard(

            @PathVariable Long id
    ) {

        // 게시글 삭제
        boardService.deleteBoard(id);
    }
}