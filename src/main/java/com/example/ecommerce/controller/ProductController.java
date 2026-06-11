package com.example.ecommerce.controller;

import com.example.ecommerce.dto.request.ProductRequest;
import com.example.ecommerce.dto.response.ProductResponse;
import com.example.ecommerce.service.ProductService;
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
@RequestMapping("/api/products")
@Tag(name = "Product API", description = "Product management endpoints")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @Operation(summary = "Create new product", description = "Insert new product into the system")
    public ResponseEntity<ProductResponse> create(@Valid @RequestBody ProductRequest productRequest){

        IO.println("Request body: "+ productRequest);

        ProductResponse productResponse = productService.create(productRequest);

        IO.println("Response body: "+ productResponse);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(productResponse);
    }

    @GetMapping
    @Operation(summary = "Get all products", description = "Retrieve all products from the system")
    public ResponseEntity<List<ProductResponse>> findAll(){

        List<ProductResponse> productResponse = productService.findAll();

        IO.println("Response body: "+ productResponse);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productResponse);
    }

    @GetMapping("/search")
    @Operation(summary = "Search products by name", description = "Retrieve products matching the given name")
    public ResponseEntity<List<ProductResponse>> findByName(@RequestParam String name){
        IO.println("Name: "+ name);

        List<ProductResponse> productResponses = productService.searchByName(name);
        IO.println("Response body: "+ productResponses);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productResponses);
    }
}
