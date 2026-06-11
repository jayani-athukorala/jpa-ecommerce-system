package com.example.ecommerce.controller;

import com.example.ecommerce.dto.request.CategoryRequest;
import com.example.ecommerce.dto.response.CategoryResponse;
import com.example.ecommerce.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categories")
@Tag(name = "Category API", description = "Category management endpoints")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    @Operation(summary = "Create new category", description = "Create new category into the system")
    public ResponseEntity<CategoryResponse> create(@Valid @RequestBody CategoryRequest categoryRequest){
        IO.println("Request body: " +categoryRequest);

        CategoryResponse categoryResponse = categoryService.create(categoryRequest);
        IO.println("Response body: "+ categoryResponse);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(categoryResponse);
    }

    @GetMapping
    @Operation(summary = "Get all categories", description = "Retrieve all categories from the system")
    public ResponseEntity<List<CategoryResponse>> findAll(){

        List<CategoryResponse> categoryResponses = categoryService.findAll();
        IO.println("Response body: "+ categoryResponses);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(categoryResponses);
    }
}
