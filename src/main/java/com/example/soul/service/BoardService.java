package com.example.soul.service;

import com.example.soul.entity.Board;
import com.example.soul.repository.BoardRepository;
import org.springframework.stereotype.Service;

import java.util.List;

// 게시판 비즈니스 로직 처리 클래스
// 告示板业务逻辑处理类
@Service
public class BoardService {

    // Repository 연결
    // 연결된 BoardRepository 객체
    private final BoardRepository boardRepository;

    // 생성자 주입
    // 构造函数注入 Repository
    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    // =========================
    // 전체 게시글 조회
    // 查询全部帖子
    // =========================
    public List<Board> getBoards() {

        // 데이터베이스의 모든 게시글 반환
        // 返回数据库中的所有帖子
        return boardRepository.findAll();
    }

    // =========================
    // 게시글 저장
    // 保存帖子
    // =========================
    public Board saveBoard(Board board) {

        // 게시글 저장
        // 将帖子保存到数据库
        return boardRepository.save(board);
    }

    // =========================
    // 게시글 상세 조회
    // 查询帖子详情
    // =========================
    public Board getBoard(Long id) {

        // 게시글 번호로 조회
        // 根据帖子编号查询

        // 없으면 null 반환
        // 如果不存在则返回 null
        return boardRepository.findById(id)
                .orElse(null);
    }

    // =========================
    // 카테고리별 게시글 조회
    // 根据分类查询帖子
    // =========================
    public List<Board> getByCategory(String category) {

        // category 값으로 조회
        // 根据 category 分类进行查询
        return boardRepository.findByCategory(category);
    }

    // =========================
    // 게시글 제목 검색
    // 根据标题搜索帖子
    // =========================
    public List<Board> search(String keyword) {

        // title에 keyword 포함 검색
        // 查询标题中包含关键字的帖子
        return boardRepository.findByTitleContaining(keyword);
    }

    // =========================
    // 게시글 삭제
    // 删除帖子
    // 작성자 이름이 일치해야 삭제 가능
    // 作者名字一致时才允许删除
    // =========================
    public void deleteBoard(Long id, String author) {

        // 게시글 조회
        // 查询帖子
        Board board = boardRepository.findById(id)
                .orElse(null);

        // 게시글이 없을 경우
        // 如果帖子不存在
        if (board == null) {

            throw new RuntimeException("게시글 없음");
        }

        // 작성자 비교
        // 比较作者名字
        if (!board.getAuthor().equals(author)) {

            throw new RuntimeException("삭제 권한 없음");
        }

        // 게시글 삭제
        // 删除帖子
        boardRepository.deleteById(id);
    }
}