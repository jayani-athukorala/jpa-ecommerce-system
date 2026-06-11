package com.example.ecommerce.controller;

import com.example.ecommerce.dto.request.PromotionRequest;
import com.example.ecommerce.dto.response.PromotionResponse;

import com.example.ecommerce.service.PromotionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/promotions")
@RequiredArgsConstructor
@Tag(name = "Promotion API", description = "Promotion management endpoints")
public class PromotionController {

    private final PromotionService promotionService;

    @PostMapping
    @Operation(summary = "Create promotion", description = "Add new promotion")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Promotion created"),
            @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    public ResponseEntity<PromotionResponse> create(
            @Valid @RequestBody PromotionRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(promotionService.create(request));
    }

    @GetMapping
    @Operation(summary = "Get active promotions", description = "Fetch active promotions")
    @ApiResponse(responseCode = "200", description = "Promotions retrieved")
    public ResponseEntity<List<PromotionResponse>> getActivePromotions() {

        return ResponseEntity.ok(promotionService.getActivePromotions());
    }
}
