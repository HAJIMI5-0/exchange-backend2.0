package com.example.soul.service;

import com.example.soul.entity.QuizQuestion;
import org.springframework.stereotype.Service;

@Service
public class QuizService {

    public QuizQuestion generateQuestion(String skill, String difficulty) {
        String s = skill.toLowerCase();
        String d = difficulty.toLowerCase();

        if (s.equals("java")) {
            if (d.equals("easy")) {
                return new QuizQuestion(skill, difficulty,
                        "What keyword is used to define a class in Java?",
                        "class");
            } else if (d.equals("medium")) {
                return new QuizQuestion(skill, difficulty,
                        "What is the difference between JDK and JRE?",
                        "JDK includes development tools, JRE is for running Java programs.");
            } else {
                return new QuizQuestion(skill, difficulty,
                        "Explain the difference between interface and abstract class in Java.",
                        "Interface defines contract, abstract class can include both abstract and concrete methods.");
            }
        }

        if (s.equals("korean")) {
            if (d.equals("easy")) {
                return new QuizQuestion(skill, difficulty,
                        "What is the Korean greeting for 'Hello'?",
                        "안녕하세요");
            } else if (d.equals("medium")) {
                return new QuizQuestion(skill, difficulty,
                        "What is the difference between 은/는 and 이/가?",
                        "은/는 is topic marker, 이/가 is subject marker.");
            } else {
                return new QuizQuestion(skill, difficulty,
                        "Explain when to use -(으)니까 in Korean grammar.",
                        "It is used to show reason or cause.");
            }
        }

        if (s.equals("english")) {
            return new QuizQuestion(skill, difficulty,
                    "Make a sentence using the word 'opportunity'.",
                    "This is an open-ended question.");
        }

        if (s.equals("python")) {
            return new QuizQuestion(skill, difficulty,
                    "What data type is returned by input() in Python?",
                    "String");
        }

        return new QuizQuestion(skill, difficulty,
                "Explain one important concept in " + skill + ".",
                "This is an open-ended question.");
    }
}