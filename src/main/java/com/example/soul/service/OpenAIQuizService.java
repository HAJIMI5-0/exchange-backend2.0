package com.example.soul.service;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.springframework.stereotype.Service;

import java.io.IOException;

//ai自动生成题目
@Service
public class OpenAIQuizService {

    private final String API_KEY = "YOUR_OPENAI_KEY";

    public String generateQuestion(String skill) throws IOException {

        OkHttpClient client = new OkHttpClient();

        String prompt = "Create a quiz question about " + skill;

        String body = "{"
                + "\"model\":\"gpt-4o-mini\","
                + "\"messages\":[{\"role\":\"user\",\"content\":\"" + prompt + "\"}]"
                + "}";

        Request request = new Request.Builder()
                .url("https://api.openai.com/v1/chat/completions")
                .addHeader("Authorization","Bearer " + API_KEY)
                .post(RequestBody.create(body, MediaType.parse("application/json")))
                .build();

        Response response = client.newCall(request).execute();

        return response.body().string();
    }
}