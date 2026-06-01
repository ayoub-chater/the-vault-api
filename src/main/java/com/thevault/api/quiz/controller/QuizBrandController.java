package com.thevault.api.quiz.controller;

import com.thevault.api.brand.dto.BrandReferenceDto;
import com.thevault.api.brand.service.BrandService;
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
public class QuizBrandController {

    private final BrandService brandService;

    @Operation(summary = "Get all brands", description = "Returns all active brands ordered by display order for the quiz brand selection screen")
    @GetMapping("/brands")
    public ResponseEntity<List<BrandReferenceDto>> getBrands() {
        return ResponseEntity.ok(brandService.getAllActiveBrands());
    }
}
