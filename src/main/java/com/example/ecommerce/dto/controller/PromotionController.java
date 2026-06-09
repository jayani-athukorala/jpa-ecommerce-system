package com.example.ecommerce.dto.controller;

import com.example.ecommerce.dto.request.PromotionRequest;
import com.example.ecommerce.dto.response.PromotionResponse;

import com.example.ecommerce.service.PromotionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/promotions")
public class PromotionController {

    private final PromotionService promotionService;

    @PostMapping
    ResponseEntity<PromotionResponse> create(@Valid @RequestBody PromotionRequest promotionRequest){
        IO.println("Request body: "+ promotionRequest);

        PromotionResponse promotionResponse = promotionService.create(promotionRequest);
        IO.println("Response body: "+ promotionResponse);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(promotionResponse);
    }

    @GetMapping
    ResponseEntity<List<PromotionResponse>> getActivePromotions(){

        List<PromotionResponse> promotionResponses = promotionService.getActivePromotions();
        IO.println("Response body: "+ promotionResponses);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(promotionResponses);
    }

}
