package com.thevault.api.common.assets.controller;

import com.thevault.api.common.assets.repository.AppAssetRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.stream.Collectors;

@Tag(name = "App Assets", description = "Dynamically configurable UI assets")
@RestController
@RequestMapping("/api/assets")
@AllArgsConstructor
public class AppAssetController {

    private final AppAssetRepository appAssetRepository;

    @Operation(summary = "Get all assets", description = "Returns all UI assets as a key-url map. Filter by prefix e.g. ?prefix=auth")
    @GetMapping
    public ResponseEntity<Map<String, String>> getAssets(
            @RequestParam(required = false) String prefix) {

        var assets = prefix != null
                ? appAssetRepository.findByKeyStartingWith(prefix)
                : appAssetRepository.findAll();

        Map<String, String> result = assets.stream()
                .collect(Collectors.toMap(a -> a.getKey(), a -> a.getUrl()));

        return ResponseEntity.ok(result);
    }
}
