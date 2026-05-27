package com.example.soul.service;

import com.example.soul.entity.Board;
import com.example.soul.entity.User;
import com.example.soul.repository.BoardRepository;
import com.example.soul.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    public BoardService(BoardRepository boardRepository,
                        UserRepository userRepository) {
        this.boardRepository = boardRepository;
        this.userRepository = userRepository;
    }

    // =========================
    // 전체 게시글 조회
    // =========================
    public List<Board> getBoards() {
        return boardRepository.findAll();
    }

    // =========================
    // 게시글 저장
    // =========================
    public Board saveBoard(Board board) {
        return boardRepository.save(board);
    }

    // =========================
    // 게시글 상세 조회
    // =========================
    public Board getBoard(Long id) {
        return boardRepository.findById(id)
                .orElse(null);
    }

    // =========================
    // 카테고리 조회
    // =========================
    public List<Board> getByCategory(String category) {
        return boardRepository.findByCategory(category);
    }

    // =========================
    // 검색
    // =========================
    public List<Board> search(String keyword) {
        return boardRepository.findByTitleContaining(keyword);
    }

    // =========================
    // 게시글 삭제 (수정 완료)
    // =========================
    public void deleteBoard(Long id, String username) {

        // 1. 게시글 조회
        Board board = boardRepository.findById(id)
                .orElse(null);

        if (board == null) {
            throw new RuntimeException("게시글 없음");
        }

        // 2. 사용자 조회
        User user = userRepository.findByUsername(username)
                .orElse(null);

        if (user == null) {
            throw new RuntimeException("사용자 없음");
        }

        // 3. 권한 판단
        boolean isAuthor = username.equals(board.getAuthor());
        boolean isAdmin = "ADMIN".equals(user.getRole());

        // 4. 권한 체크
        if (!isAuthor && !isAdmin) {
            throw new RuntimeException("삭제 권한 없음");
        }

        // 5. 삭제 실행
        boardRepository.deleteById(id);
    }
}