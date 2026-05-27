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

    public List<Board> getBoards() {
        return boardRepository.findAll();
    }

    public Board saveBoard(Board board) {
        return boardRepository.save(board);
    }

    public Board getBoard(Long id) {
        return boardRepository.findById(id)
                .orElse(null);
    }

    public List<Board> getByCategory(String category) {
        return boardRepository.findByCategory(category);
    }

    public List<Board> search(String keyword) {
        return boardRepository.findByTitleContaining(keyword);
    }

    // =========================
    // ⭐最终修复版删除逻辑
    // =========================
    public void deleteBoard(Long id, String username) {

        Board board = boardRepository.findById(id)
                .orElse(null);

        if (board == null) {
            throw new RuntimeException("게시글 없음");
        }

        User user = userRepository.findByUsername(username)
                .orElse(null);

        if (user == null) {
            throw new RuntimeException("사용자 없음");
        }

        // ✔ 修复点1：防止 null + 空格问题
        String role = user.getRole() == null ? "" : user.getRole().trim();

        boolean isAuthor = username.equals(board.getAuthor());
        boolean isAdmin = "ADMIN".equalsIgnoreCase(role);

        // ✔ 修复点2：统一权限逻辑
        if (!isAuthor && !isAdmin) {
            throw new RuntimeException("삭제 권한 없음");
        }

        boardRepository.deleteById(id);
    }
}