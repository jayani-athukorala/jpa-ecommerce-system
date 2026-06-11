package com.example.ecommerce.controller;

import com.example.ecommerce.dto.request.ProductRequest;
import com.example.ecommerce.dto.response.ProductResponse;
import com.example.ecommerce.service.ProductService;
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
@RequestMapping("/api/products")
@RequiredArgsConstructor
@Tag(name = "Product API", description = "Product management endpoints")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @Operation(summary = "Create product", description = "Insert new product")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Product created"),
            @ApiResponse(responseCode = "400", description = "Invalid request data")
    })
    public ResponseEntity<ProductResponse> create(
            @Valid @RequestBody ProductRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(productService.create(request));
    }

    @GetMapping
    @Operation(summary = "Get all products", description = "Retrieve all products")
    @ApiResponse(responseCode = "200", description = "List of products retrieved")
    public ResponseEntity<List<ProductResponse>> findAll() {

        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("/search")
    @Operation(summary = "Search products", description = "Search products by name")
    @ApiResponse(responseCode = "200", description = "Search results returned")
    public ResponseEntity<List<ProductResponse>> findByName(
            @RequestParam String name) {

        return ResponseEntity.ok(productService.searchByName(name));
    }
}
