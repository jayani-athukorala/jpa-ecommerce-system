package com.example.ecommerce.controller;

import com.example.ecommerce.dto.request.CustomerRequest;
import com.example.ecommerce.dto.response.CustomerResponse;
import com.example.ecommerce.service.CustomerService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerResponse> register(@Valid @RequestBody CustomerRequest customerRequest){
        IO.println("Request body: "+ customerRequest);

        CustomerResponse customerResponse = customerService.register(customerRequest);
        IO.println("Response body: "+ customerResponse);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(customerResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> findById(@PathVariable @Positive Long id){
        IO.println("Id: "+ id);

        CustomerResponse customerResponse = customerService.findById(id);
        IO.println("Response body: "+ customerResponse);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(customerResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponse> update(@PathVariable Long id,@Valid @RequestBody CustomerRequest customerRequest){
        IO.println("Request body: "+ customerRequest);

        CustomerResponse customerResponse = customerService.update(id, customerRequest);
        IO.println("Response body: "+ customerResponse);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(customerResponse);
    }
}
