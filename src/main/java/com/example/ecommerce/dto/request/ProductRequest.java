package com.example.ecommerce.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProductRequest(
        @NotBlank(message = "Product name cannot be empty!")
        String name,

        @NotNull(message = "Stock cannot be empty!")
        @Positive(message = "Stock must be positive!")
        Integer stock,

        @NotNull(message = "Price cannot be null!")
        @Positive(message = "Price must be positive!")
        BigDecimal price,

        @NotNull(message = "Category ID cannot be null!")
        Long categoryId
) {
}
