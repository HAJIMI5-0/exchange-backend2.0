package com.example.soul.controller;

import com.example.soul.dto.AiSuggestRequest;
import com.example.soul.dto.ChatSendRequest;
import com.example.soul.entity.ChatMessage;
import com.example.soul.repository.ChatMessageRepository;
import com.example.soul.service.OllamaService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/chat")
@CrossOrigin(origins = "*")
public class ChatController {

    private final ChatMessageRepository chatMessageRepository;
    private final OllamaService ollamaService;

    public ChatController(
            ChatMessageRepository chatMessageRepository,
            OllamaService ollamaService
    ) {
        this.chatMessageRepository = chatMessageRepository;
        this.ollamaService = ollamaService;
    }

    @PostMapping("/send")
    public Map<String, Object> sendMessage(@RequestBody ChatSendRequest request) {
        Map<String, Object> result = new HashMap<>();

        if (request.getSenderUsername() == null ||
                request.getReceiverUsername() == null ||
                request.getContent() == null ||
                request.getContent().trim().isEmpty()) {

            result.put("success", false);
            result.put("message", "메시지 내용이 비어 있습니다.");
            return result;
        }

        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setSenderUsername(request.getSenderUsername());
        chatMessage.setReceiverUsername(request.getReceiverUsername());
        chatMessage.setContent(request.getContent());

        ChatMessage savedMessage = chatMessageRepository.save(chatMessage);

        result.put("success", true);
        result.put("message", savedMessage);

        return result;
    }

    @GetMapping("/messages")
    public List<ChatMessage> getMessages(
            @RequestParam String me,
            @RequestParam String partner
    ) {
        return chatMessageRepository.findMessagesBetweenUsers(me, partner);
    }

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