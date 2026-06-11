package com.example.ecommerce.controller;

import com.example.ecommerce.dto.request.CustomerRequest;
import com.example.ecommerce.dto.response.CustomerResponse;
import com.example.ecommerce.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
@Tag(name = "Customer API", description = "Customer management endpoints")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    @Operation(summary = "Create customer", description = "Registers a new customer")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Customer created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request data"),
            @ApiResponse(responseCode = "409", description = "Email already exists")
    })
    public ResponseEntity<CustomerResponse> register(@Valid @RequestBody CustomerRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(customerService.register(request));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get customer by ID", description = "Fetch customer using ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Customer found"),
            @ApiResponse(responseCode = "404", description = "Customer not found")
    })
    public ResponseEntity<CustomerResponse> findById(@PathVariable @Positive Long id) {

        return ResponseEntity.ok(customerService.findById(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update customer", description = "Update existing customer")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Customer updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request data"),
            @ApiResponse(responseCode = "404", description = "Customer not found")
    })
    public ResponseEntity<CustomerResponse> update(@PathVariable @Positive Long id, @Valid @RequestBody CustomerRequest request) {

        return ResponseEntity.ok(customerService.update(id, request));
    }
}
