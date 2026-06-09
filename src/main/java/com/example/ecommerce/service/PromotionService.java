package com.example.ecommerce.service;

import com.example.ecommerce.dto.request.PromotionRequest;
import com.example.ecommerce.dto.response.PromotionResponse;
import com.example.ecommerce.entity.Product;

import java.math.BigDecimal;
import java.util.List;

public interface PromotionService {
    PromotionResponse create(PromotionRequest promotionRequest);
    List<PromotionResponse> getActivePromotions();
    BigDecimal calculateDiscount(Product product);
}
