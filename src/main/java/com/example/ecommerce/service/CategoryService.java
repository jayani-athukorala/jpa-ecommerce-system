package com.example.ecommerce.service;

import com.example.ecommerce.dto.request.CategoryRequest;
import com.example.ecommerce.dto.response.CategoryResponse;

import java.util.List;

public interface CategoryService {
    CategoryResponse create(CategoryRequest categoryRequest);
    List<CategoryResponse> findAll();
}
