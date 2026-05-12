package com.example.soul.repository;

import com.example.soul.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {

    // 查询当前用户参与的所有聊天室
    List<ChatRoom> findByUser1IdOrUser2Id(Long user1Id, Long user2Id);

    // 双向查询两个用户之间的聊天室
    @Query("""
        SELECT r FROM ChatRoom r
        WHERE
        (r.user1Id = :userA AND r.user2Id = :userB)
        OR
        (r.user1Id = :userB AND r.user2Id = :userA)
    """)
    Optional<ChatRoom> findRoomBetweenUsers(
            @Param("userA") Long userA,
            @Param("userB") Long userB
    );
}