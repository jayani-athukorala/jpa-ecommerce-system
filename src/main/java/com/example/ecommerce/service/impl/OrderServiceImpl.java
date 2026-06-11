package com.example.ecommerce.service.impl;

import com.example.ecommerce.dto.request.OrderItemRequest;
import com.example.ecommerce.dto.request.OrderRequest;
import com.example.ecommerce.dto.response.OrderResponse;
import com.example.ecommerce.entity.*;
import com.example.ecommerce.exception.ResourceNotFoundException;
import com.example.ecommerce.mapper.OrderMapper;
import com.example.ecommerce.repository.CustomerRepository;
import com.example.ecommerce.repository.OrderRepository;
import com.example.ecommerce.repository.ProductRepository;
import com.example.ecommerce.service.OrderService;
import com.example.ecommerce.service.PromotionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final PromotionService promotionService;
    private final OrderMapper orderMapper;

    @Override
    @Transactional
    public OrderResponse placeOrder(OrderRequest orderRequest) {
        if (orderRequest == null) throw new IllegalArgumentException("OrderRequest cannot be null!");

        // Find the customer
        Customer customer = customerRepository.findById(orderRequest.customerId())
                .orElseThrow(()-> new ResourceNotFoundException("Customer not found with id "+orderRequest.customerId()));

        Order order = new Order();
        order.setCustomer(customer);
        order.setStatus(OrderStatus.CREATED);
        List<OrderItem> orderItems = new ArrayList<>();

        for (OrderItemRequest itemRequest : orderRequest.items()) {

            // Find the Product for each requested item
            Product product = productRepository.findById(itemRequest.productId())
                    .orElseThrow(() ->
                            new ResourceNotFoundException(
                                    "Product not found with id " + itemRequest.productId()));

            // Stock validation
            if (product.getStock() < itemRequest.quantity()) {
                throw new IllegalArgumentException(
                        "Insufficient stock for product with id " + product.getName());
            }

            // Apply any active Promotion.
            BigDecimal discountPrice = promotionService.calculateDiscount(product);

            BigDecimal finalPrice = product.getPrice().subtract(discountPrice);

            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(product);
            orderItem.setQuantity(itemRequest.quantity());
            orderItem.setPriceAtPurchase(finalPrice);
            orderItem.setOrder(order);

            orderItems.add(orderItem);

            // Stock update
            product.setStock(product.getStock() - itemRequest.quantity());
            productRepository.save(product);
        }

        // Save the Order with its items.
        order.setItems(orderItems);
        Order savedOrder = orderRepository.save(order);

        return orderMapper.toResponse(savedOrder);
     }
}
