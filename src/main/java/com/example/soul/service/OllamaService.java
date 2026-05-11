package com.example.soul.service;

import com.example.soul.dto.AiSuggestRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
public class OllamaService {

    @Value("${ollama.base-url}")
    private String ollamaBaseUrl;

    @Value("${ollama.model}")
    private String ollamaModel;

    private final RestTemplate restTemplate = new RestTemplate();

    public String suggestReply(AiSuggestRequest request) {

        String userMessage = request.getMessage();

        if (userMessage == null || userMessage.trim().isEmpty()) {
            userMessage = "상대방과 기술 교환 대화를 시작하고 싶습니다.";
        }

        String prompt = """
                당신은 SOUL 기술 교환 플랫폼의 채팅 도우미입니다.
                사용자가 상대방에게 보낼 수 있는 자연스럽고 짧은 한국어 답장을 추천하세요.
                조건:
                1. 한 문장 또는 두 문장으로 답하세요.
                2. 너무 길게 설명하지 마세요.
                3. 기술 교환, 스터디, 학습 약속과 관련된 자연스러운 말투로 답하세요.
                4. 따옴표 없이 답장 내용만 출력하세요.

                현재 사용자가 입력한 내용:
                """ + userMessage;

        Map<String, Object> body = Map.of(
                "model", ollamaModel,
                "stream", false,
                "messages", List.of(
                        Map.of(
                                "role", "system",
                                "content", "당신은 기술 교환 플랫폼의 친절한 채팅 추천 AI입니다."
                        ),
                        Map.of(
                                "role", "user",
                                "content", prompt
                        )
                )
        );

        Map response = restTemplate.postForObject(
                ollamaBaseUrl + "/api/chat",
                body,
                Map.class
        );

        if (response == null || response.get("message") == null) {
            return "같이 스터디를 진행해보면 좋을 것 같아요!";
        }

        Map message = (Map) response.get("message");

        Object content = message.get("content");

        if (content == null) {
            return "같이 스터디를 진행해보면 좋을 것 같아요!";
        }

        return content.toString().trim();
    }
}