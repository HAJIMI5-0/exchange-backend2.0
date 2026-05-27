package com.example.soul.controller;

import com.example.soul.entity.Board;
import com.example.soul.service.BoardService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// REST API 컨트롤러
// REST API 控制器
@RestController

// 기본 주소 설정
// 基础请求地址
// localhost:8080/api/board
@RequestMapping("/api/board")

// React 연결 허용
// 允许 React 前端跨域访问
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

    // Service 연결
    // 连接 BoardService
    private final BoardService boardService;

    // 생성자 주입
    // 构造函数注入
    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    // =========================
    // 전체 게시글 조회
    // 查询全部帖子
    // GET /api/board
    // =========================
    @GetMapping
    public List<Board> getBoards() {

        // 모든 게시글 반환
        // 返回所有帖子
        return boardService.getBoards();
    }

    // =========================
    // 게시글 작성
    // 创建帖子
    // POST /api/board
    // =========================
    @PostMapping
    public Board createBoard(

            // React JSON 데이터 받기
            // 接收 React 发送的 JSON 数据
            @RequestBody Board board
    ) {

        // 게시글 저장
        // 保存帖子
        return boardService.saveBoard(board);
    }

    // =========================
    // 게시글 상세 조회
    // 查询帖子详情
    // GET /api/board/1
    // =========================
    @GetMapping("/{id}")
    public Board getBoard(

            // URL의 id 값 받기
            // 获取 URL 中的 id
            @PathVariable Long id
    ) {

        // 게시글 하나 조회
        // 查询单个帖子
        return boardService.getBoard(id);
    }

    // =========================
    // 카테고리별 게시글 조회
    // 根据分类查询帖子
    // GET /api/board/category/질문게시판
    // =========================
    @GetMapping("/category/{category}")
    public List<Board> getCategoryBoards(

            // URL category 값 받기
            // 获取 URL 中的 category
            @PathVariable String category
    ) {

        // category별 조회
        // 根据分类查询
        return boardService.getByCategory(category);
    }

    // =========================
    // 게시글 검색
    // 搜索帖子
    // GET /api/board/search?keyword=spring
    // =========================
    @GetMapping("/search")
    public List<Board> searchBoards(

            // keyword 파라미터 받기
            // 获取 keyword 参数
            @RequestParam String keyword
    ) {

        // 제목 검색
        // 根据标题搜索
        return boardService.search(keyword);
    }

    // =========================
    // 게시글 삭제
    // 删除帖子
    // DELETE /api/board/1?username=gxc
    // username 일치해야 삭제 가능
    // username 一致时才允许删除
    // =========================
    @DeleteMapping("/{id}")
    public void deleteBoard(

            // URL의 id 값 받기
            // 获取 URL 中的 id
            @PathVariable Long id,

            // username 받기
            // 获取 username
            @RequestParam String username
    ) {

        // 게시글 삭제
        // 删除帖子
        boardService.deleteBoard(id, username);
    }
}