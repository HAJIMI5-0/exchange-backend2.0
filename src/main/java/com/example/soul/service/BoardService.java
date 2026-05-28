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

        List<Board> boards =
                boardRepository.findAll();

        for (Board board : boards) {

            User user =
                    userRepository
                            .findByUsername(board.getUsername())
                            .orElse(null);

            if (user != null) {

                board.setName(user.getName());

                board.setAvatar(user.getAvatar());
            }
        }

        return boards;
    }

    public Board saveBoard(Board board) {
        return boardRepository.save(board);
    }

    public Board getBoard(Long id) {

        Board board =
                boardRepository.findById(id)
                        .orElse(null);

        if (board == null) {
            return null;
        }

        User user =
                userRepository
                        .findByUsername(board.getUsername())
                        .orElse(null);

        if (user != null) {

            board.setName(user.getName());

            board.setAvatar(user.getAvatar());
        }

        return board;
    }

    public List<Board> getByCategory(String category) {
        return boardRepository.findByCategory(category);
    }

    public List<Board> search(String keyword) {
        return boardRepository.findByTitleContaining(keyword);
    }

    // =========================
    // 删除逻辑（已统一 username）
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

        String role = user.getRole() == null ? "" : user.getRole().trim();

        // ✔ 关键修复：统一字段
        boolean isAuthor = username.equals(board.getUsername());
        boolean isAdmin = "ADMIN".equalsIgnoreCase(role);

        if (!isAuthor && !isAdmin) {
            throw new RuntimeException("삭제 권한 없음");
        }

        boardRepository.deleteById(id);
    }
}