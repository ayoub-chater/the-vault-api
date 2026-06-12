package com.thevault.api.zebra.controller;

import com.thevault.api.quiz.dto.QuizSubmitRequestDto;
import com.thevault.api.zebra.dto.ZebraMatchResultDto;
import com.thevault.api.zebra.dto.ZebraSessionDto;
import com.thevault.api.zebra.service.ZebraMatchingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "ZEBRA", description = "The Vault's AI image consultant matching")
@RestController
@RequestMapping("/api/zebra/sessions")
@AllArgsConstructor
public class ZebraController {

    private final ZebraMatchingService zebraMatchingService;

    @Operation(summary = "Start a ZEBRA session", description = "Returns the user's in-progress session if one exists, otherwise starts a new one")
    @PostMapping
    public ResponseEntity<ZebraSessionDto> startSession() {
        return ResponseEntity.ok(zebraMatchingService.startSession());
    }

    @Operation(summary = "Submit style quiz answers", description = "Records the StyleProfile answers powering the ZEBRA match for this session")
    @PostMapping("/{id}/submit")
    public ResponseEntity<ZebraSessionDto> submit(@PathVariable Long id, @RequestBody QuizSubmitRequestDto request) {
        return ResponseEntity.ok(zebraMatchingService.submitAnswers(id, request));
    }

    @Operation(summary = "Get the ZEBRA consultant match", description = "Runs the matching algorithm against the session's StyleProfile and returns the best-fit image consultant")
    @GetMapping("/{id}/match")
    public ResponseEntity<ZebraMatchResultDto> match(@PathVariable Long id) {
        return ResponseEntity.ok(zebraMatchingService.matchConsultant(id));
    }
}
