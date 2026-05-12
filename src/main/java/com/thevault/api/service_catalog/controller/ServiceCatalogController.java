package com.thevault.api.service_catalog.controller;

import com.thevault.api.service_catalog.dto.ServiceCatalogDto;
import com.thevault.api.service_catalog.service.ServiceCatalogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Services", description = "App service catalog for the onboarding carousel")
@RestController
@RequestMapping("/api/services")
@AllArgsConstructor
public class ServiceCatalogController {

    private final ServiceCatalogService serviceCatalogService;

    @Operation(summary = "Get all services", description = "Returns all 10 Vault services ordered by display order")
    @GetMapping
    public ResponseEntity<List<ServiceCatalogDto>> getAllServices() {
        return ResponseEntity.ok(serviceCatalogService.getAllServices());
    }
}
