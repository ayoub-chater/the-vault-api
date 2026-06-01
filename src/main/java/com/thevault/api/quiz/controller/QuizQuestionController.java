package com.thevault.api.quiz.controller;

import com.thevault.api.quiz.dto.QuizQuestionDto;
import com.thevault.api.quiz.service.QuizQuestionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Quiz", description = "Main Quiz endpoints")
@RestController
@RequestMapping("/api/quiz")
@AllArgsConstructor
public class QuizQuestionController {

    private final QuizQuestionService quizQuestionService;

    @Operation(
        summary = "Get all quiz questions",
        description = "Returns all 37 quiz questions ordered by display order, with options nested inside each question"
    )
    @GetMapping("/questions")
    public ResponseEntity<List<QuizQuestionDto>> getQuestions() {
        return ResponseEntity.ok(quizQuestionService.getAllQuestions());
    }
}
