package com.example.ecommerce.dto.response;

import java.math.BigDecimal;

public record ProductResponse(
        Long id,
        String name,
        Integer stock,
        BigDecimal price,
        String categoryName
) {
}
