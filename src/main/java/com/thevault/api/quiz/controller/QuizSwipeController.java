package com.thevault.api.quiz.controller;

import com.thevault.api.quiz.dto.StyleSwipeImageDto;
import com.thevault.api.quiz.service.StyleSwipeImageService;
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
public class QuizSwipeController {

    private final StyleSwipeImageService swipeImageService;

    @Operation(
        summary = "Get style swipe images",
        description = "Returns all active outfit images for the Style Swipe section, ordered by display order"
    )
    @GetMapping("/swipe-images")
    public ResponseEntity<List<StyleSwipeImageDto>> getSwipeImages() {
        return ResponseEntity.ok(swipeImageService.getAllActiveImages());
    }
}
