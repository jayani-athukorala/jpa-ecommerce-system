package com.example.ecommerce.service.impl;

import com.example.ecommerce.dto.request.PromotionRequest;
import com.example.ecommerce.dto.response.PromotionResponse;
import com.example.ecommerce.entity.Product;
import com.example.ecommerce.entity.Promotion;
import com.example.ecommerce.exception.ResourceNotFoundException;
import com.example.ecommerce.mapper.PromotionMapper;
import com.example.ecommerce.repository.ProductRepository;
import com.example.ecommerce.repository.PromotionRepository;
import com.example.ecommerce.service.PromotionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PromotionServiceImpl implements PromotionService {

    private final PromotionRepository promotionRepository;
    private final ProductRepository productRepository;
    private final PromotionMapper promotionMapper;

    @Override
    @Transactional
    public PromotionResponse create(PromotionRequest promotionRequest) {

        if (promotionRequest.endDate().isBefore(promotionRequest.startDate()))
            throw new IllegalArgumentException("End date must be after start date!");

        if (promotionRepository.existsByCode(promotionRequest.code()))
            throw new IllegalArgumentException("Promotion code already exists!");

        if (promotionRequest.productIds() == null || promotionRequest.productIds().isEmpty())
            throw new IllegalArgumentException("Product list cannot be empty!");

        Promotion promotion = promotionMapper.toEntity(promotionRequest);

        List<Product> products = productRepository.findAllById(promotionRequest.productIds());

        if (products.size() != promotionRequest.productIds().size()) {
            throw new ResourceNotFoundException("One or more products not found");
        }

        // Save promotion
        Promotion savedPromotion = promotionRepository.save(promotion);

        // sync BOTH sides
        for (Product product : products) {
            product.getPromotions().add(promotion);
        }

        productRepository.saveAll(products);
        savedPromotion.setProducts(new HashSet<>(products));

        return promotionMapper.toResponse(savedPromotion);
    }


    @Override
    @Transactional(readOnly = true)
    public List<PromotionResponse> getActivePromotions() {
        return promotionRepository.findActiveToday()
                .stream()
                .map(promotionMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public BigDecimal calculateDiscount(Product product) {
        if(product == null) throw new IllegalArgumentException("Product cannot be null!");

        BigDecimal price = product.getPrice();

        BigDecimal maxDiscountPercentage = product.getPromotions()
                .stream()
                .filter(p ->
                        !p.getStartDate().isAfter(LocalDate.now()) &&
                                !p.getEndDate().isBefore(LocalDate.now())
                )
                .map(Promotion::getDiscountPercentage)
                .max(BigDecimal::compareTo)
                .orElse(BigDecimal.ZERO);

        return price.multiply(maxDiscountPercentage)
                .divide(BigDecimal.valueOf(100), RoundingMode.HALF_UP);
    }
}
