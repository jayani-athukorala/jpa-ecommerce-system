package com.example.ecommerce.dto.controller;

import com.example.ecommerce.dto.request.CategoryRequest;
import com.example.ecommerce.dto.response.CategoryResponse;
import com.example.ecommerce.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryResponse> create(@Valid @RequestBody CategoryRequest categoryRequest){
        IO.println("Request body: " +categoryRequest);

        CategoryResponse categoryResponse = categoryService.create(categoryRequest);
        IO.println("Response body: "+ categoryResponse);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(categoryResponse);
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> findAll(){

        List<CategoryResponse> categoryResponses = categoryService.findAll();
        IO.println("Response body: "+ categoryResponses);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(categoryResponses);
    }
}
