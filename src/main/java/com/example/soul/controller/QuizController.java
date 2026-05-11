package com.example.soul.controller;

import com.example.soul.entity.QuizQuestion;
import com.example.soul.service.QuizService;
import org.springframework.web.bind.annotation.*;


//问卷接口
@RestController
@RequestMapping("/quiz")
public class QuizController {

    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @GetMapping
    public QuizQuestion generateQuiz(
            @RequestParam String skill,
            @RequestParam String difficulty
    ) {
        return quizService.generateQuestion(skill, difficulty);
    }
}