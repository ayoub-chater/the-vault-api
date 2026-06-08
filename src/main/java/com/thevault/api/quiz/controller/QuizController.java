package com.thevault.api.quiz.controller;

import com.thevault.api.quiz.dto.QuizSubmitRequestDto;
import com.thevault.api.quiz.dto.StyleProfileDto;
import com.thevault.api.quiz.service.QuizService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Quiz", description = "Main Quiz endpoints")
@RestController
@RequestMapping("/api/quiz")
@AllArgsConstructor
public class QuizController {

    private final QuizService quizService;

    @Operation(
        summary = "Submit the quiz",
        description = "Persists the user's complete quiz answers as their StyleProfile and marks it as completed. Awards +30 keys on first submission."
    )
    @PostMapping("/submit")
    public ResponseEntity<StyleProfileDto> submit(@Valid @RequestBody QuizSubmitRequestDto request) {
        return ResponseEntity.ok(quizService.submit(request));
    }

    @Operation(
        summary = "Get my style profile",
        description = "Returns the current user's completed StyleProfile"
    )
    @GetMapping("/me")
    public ResponseEntity<StyleProfileDto> getMyProfile() {
        return ResponseEntity.ok(quizService.getMyProfile());
    }

    @Operation(
        summary = "Update my style profile",
        description = "Allows the user to retake or update their quiz answers"
    )
    @PutMapping("/me")
    public ResponseEntity<StyleProfileDto> update(@Valid @RequestBody QuizSubmitRequestDto request) {
        return ResponseEntity.ok(quizService.update(request));
    }
}
