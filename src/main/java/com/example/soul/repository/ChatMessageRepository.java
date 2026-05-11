package com.example.soul.repository;

import com.example.soul.entity.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {

    @Query("""
        SELECT m FROM ChatMessage m
        WHERE 
        (m.senderUsername = :me AND m.receiverUsername = :partner)
        OR
        (m.senderUsername = :partner AND m.receiverUsername = :me)
        ORDER BY m.createdAt ASC
    """)
    List<ChatMessage> findMessagesBetweenUsers(
            @Param("me") String me,
            @Param("partner") String partner
    );
}