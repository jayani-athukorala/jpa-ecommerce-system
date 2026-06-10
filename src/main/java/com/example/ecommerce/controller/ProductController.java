package com.example.ecommerce.controller;

import com.example.ecommerce.dto.request.ProductRequest;
import com.example.ecommerce.dto.response.ProductResponse;
import com.example.ecommerce.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponse> create(@Valid @RequestBody ProductRequest productRequest){

        IO.println("Request body: "+ productRequest);

        ProductResponse productResponse = productService.create(productRequest);

        IO.println("Response body: "+ productResponse);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(productResponse);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> findAll(){

        List<ProductResponse> productResponse = productService.findAll();

        IO.println("Response body: "+ productResponse);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productResponse);
    }

    @GetMapping("/search")
    public ResponseEntity<List<ProductResponse>> findByName(@RequestParam String name){
        IO.println("Name: "+ name);

        List<ProductResponse> productResponses = productService.searchByName(name);
        IO.println("Response body: "+ productResponses);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productResponses);
    }
}
