package com.example.ecommerce.mapper;

import com.example.ecommerce.dto.request.PromotionRequest;
import com.example.ecommerce.dto.response.PromotionResponse;
import com.example.ecommerce.entity.Product;
import com.example.ecommerce.entity.Promotion;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PromotionMapper {

    public PromotionResponse toResponse(Promotion promotion){
        if(promotion == null) throw new IllegalArgumentException("Promotion cannot be null!");

        List<Long> productIds =
                promotion.getProducts()
                        .stream()
                        .map(Product::getId)
                        .toList();

        return new PromotionResponse(
                promotion.getId(),
                promotion.getCode(),
                promotion.getStartDate(),
                promotion.getEndDate(),
                promotion.getDiscountPercentage(),
                productIds
        );
    }

    public Promotion toEntity(PromotionRequest promotionRequest){
        Promotion promotion = new Promotion();
        promotion.setCode(promotionRequest.code());
        promotion.setStartDate(promotionRequest.startDate());
        promotion.setEndDate(promotionRequest.endDate());
        promotion.setDiscountPercentage(promotionRequest.discountPercentage());

        return promotion;
    }
}
