package com.example.ecommerce.controller;

import com.example.ecommerce.dto.request.CustomerRequest;
import com.example.ecommerce.dto.response.CustomerResponse;
import com.example.ecommerce.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "Create new customer", description = "Registers a new customer in the system")
    public ResponseEntity<CustomerResponse> register(@Valid @RequestBody CustomerRequest customerRequest){
        IO.println("Request body: "+ customerRequest);

        CustomerResponse customerResponse = customerService.register(customerRequest);
        IO.println("Response body: "+ customerResponse);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(customerResponse);
    }

    @GetMapping("/{id}")
    @Operation( summary = "Find customer by ID", description = "Retrieves a customer using their ID")
    public ResponseEntity<CustomerResponse> findById(@PathVariable @Positive Long id){
        IO.println("Id: "+ id);

        CustomerResponse customerResponse = customerService.findById(id);
        IO.println("Response body: "+ customerResponse);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(customerResponse);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update customer", description = "Updates an existing customer by ID")
    public ResponseEntity<CustomerResponse> update(@Positive @PathVariable Long id,@Valid @RequestBody CustomerRequest customerRequest){
        IO.println("Request body: "+ customerRequest);

        CustomerResponse customerResponse = customerService.update(id, customerRequest);
        IO.println("Response body: "+ customerResponse);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(customerResponse);
    }
}
