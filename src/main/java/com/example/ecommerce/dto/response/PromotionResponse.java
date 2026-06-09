package com.example.ecommerce.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record PromotionResponse(
    Long id,
    String code,
    LocalDate startDate,
    LocalDate endDate,
    BigDecimal discountPercentage,
    List<Long> products
    ){
}
