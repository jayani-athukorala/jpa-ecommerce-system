package com.example.ecommerce.dto.request;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record PromotionRequest(
        String code,
        LocalDate startDate,
        LocalDate endDate,
        BigDecimal discountPercentage,
        List<Long> productIds
) {
}
