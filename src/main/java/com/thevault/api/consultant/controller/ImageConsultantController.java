package com.thevault.api.consultant.controller;

import com.thevault.api.consultant.dto.ImageConsultantDto;
import com.thevault.api.consultant.service.ImageConsultantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Image Consultants", description = "Vault Image Consultants matched by ZEBRA")
@RestController
@RequestMapping("/api/consultants")
@AllArgsConstructor
public class ImageConsultantController {

    private final ImageConsultantService imageConsultantService;

    @Operation(summary = "Get an image consultant by id", description = "Returns the consultant profile with their portfolio gallery")
    @GetMapping("/{id}")
    public ResponseEntity<ImageConsultantDto> getConsultantById(@PathVariable Long id) {
        return ResponseEntity.ok(imageConsultantService.getConsultantById(id));
    }
}
