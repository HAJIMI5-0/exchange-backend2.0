package com.example.soul.controller;

import com.example.soul.dto.AiSuggestRequest;
import com.example.soul.dto.ChatSendRequest;
import com.example.soul.entity.ChatMessage;
import com.example.soul.entity.ChatRoom;
import com.example.soul.repository.ChatMessageRepository;
import com.example.soul.repository.ChatRoomRepository;
import com.example.soul.repository.UserRepository;
import com.example.soul.service.OllamaService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/chat")
@CrossOrigin(origins = "*")
public class ChatController {

    private final ChatMessageRepository chatMessageRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final UserRepository userRepository;
    private final OllamaService ollamaService;

    public ChatController(
            ChatMessageRepository chatMessageRepository,
            ChatRoomRepository chatRoomRepository,
            UserRepository userRepository,
            OllamaService ollamaService
    ) {
        this.chatMessageRepository = chatMessageRepository;
        this.chatRoomRepository = chatRoomRepository;
        this.userRepository = userRepository;
        this.ollamaService = ollamaService;
    }

    // =========================
    // 发送消息（只有匹配成功用户可发送）
    // =========================
    @PostMapping("/send")
    public Map<String, Object> sendMessage(@RequestBody ChatSendRequest request) {
        Map<String, Object> result = new HashMap<>();

        if (request.getSenderUsername() == null || request.getSenderUsername().trim().isEmpty()) {
            result.put("success", false);
            result.put("message", "senderUsername이 비어 있습니다.");
            return result;
        }

        if (request.getReceiverUsername() == null || request.getReceiverUsername().trim().isEmpty()) {
            result.put("success", false);
            result.put("message", "receiverUsername이 비어 있습니다.");
            return result;
        }

        if (request.getContent() == null || request.getContent().trim().isEmpty()) {
            result.put("success", false);
            result.put("message", "content가 비어 있습니다.");
            return result;
        }

        try {
            var sender = userRepository.findByUsername(request.getSenderUsername());
            var receiver = userRepository.findByUsername(request.getReceiverUsername());

            if (sender.isEmpty() || receiver.isEmpty()) {
                result.put("success", false);
                result.put("message", "사용자를 찾을 수 없습니다.");
                return result;
            }

            Long senderId = sender.get().getId();
            Long receiverId = receiver.get().getId();

            var room = chatRoomRepository.findRoomBetweenUsers(senderId, receiverId);

            if (room.isEmpty()) {
                result.put("success", false);
                result.put("message", "매칭된 채팅방이 없습니다.");
                return result;
            }

            ChatMessage chatMessage = new ChatMessage();
            chatMessage.setSenderUsername(request.getSenderUsername());
            chatMessage.setReceiverUsername(request.getReceiverUsername());
            chatMessage.setContent(request.getContent());

            ChatMessage savedMessage = chatMessageRepository.save(chatMessage);

            ChatRoom chatRoom = room.get();
            chatRoom.setLastMessage(request.getContent());
            chatRoom.setLastSenderType("USER");
            chatRoom.setLastTime(LocalDateTime.now());

            chatRoomRepository.save(chatRoom);

            result.put("success", true);
            result.put("message", savedMessage);

            return result;

        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "채팅 메시지 저장 실패");
            result.put("error", e.getMessage());
            return result;
        }
    }

    // =========================
    // 获取聊天记录（只有聊天室成员可查看）
    // =========================
    @GetMapping("/messages")
    public List<ChatMessage> getMessages(
            @RequestParam String me,
            @RequestParam String partner
    ) {
        var meUser = userRepository.findByUsername(me);
        var partnerUser = userRepository.findByUsername(partner);

        if (meUser.isEmpty() || partnerUser.isEmpty()) {
            return List.of();
        }

        Long meId = meUser.get().getId();
        Long partnerId = partnerUser.get().getId();

        var room = chatRoomRepository.findRoomBetweenUsers(meId, partnerId);

        if (room.isEmpty()) {
            return List.of();
        }

        return chatMessageRepository.findMessagesBetweenUsers(me, partner);
    }

    // =========================
    // 获取当前用户聊天室列表
    // =========================
    @GetMapping("/rooms")
    public List<Map<String, Object>> getMyRooms(
            @RequestParam String username
    ) {
        var user = userRepository.findByUsername(username);

        if (user.isEmpty()) {
            return List.of();
        }

        Long myId = user.get().getId();

        var rooms = chatRoomRepository.findByUser1IdOrUser2Id(myId, myId);

        return rooms.stream().map(room -> {
            Long partnerId;

            if (room.getUser1Id().equals(myId)) {
                partnerId = room.getUser2Id();
            } else {
                partnerId = room.getUser1Id();
            }

            var partner = userRepository.findById(partnerId);

            Map<String, Object> roomData = new HashMap<>();

            roomData.put("roomId", room.getId());
            roomData.put("partnerId", partnerId);
            roomData.put(
                    "partnerUsername",
                    partner.map(u -> u.getUsername()).orElse("")
            );
            roomData.put(
                    "partnerName",
                    partner.map(u -> u.getName()).orElse("")
            );
            roomData.put("lastMessage", room.getLastMessage());
            roomData.put("lastTime", room.getLastTime());

            return roomData;

        }).collect(Collectors.toList());
    }

    // =========================
    // AI 回复建议
    // =========================
    @PostMapping("/ai-suggest")
    public Map<String, Object> aiSuggest(@RequestBody AiSuggestRequest request) {
        Map<String, Object> result = new HashMap<>();

        try {
            String suggestion = ollamaService.suggestReply(request);

            result.put("success", true);
            result.put("suggestion", suggestion);

            return result;

        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "AI 추천 생성 실패");
            result.put("error", e.getMessage());

            return result;
        }
    }
}