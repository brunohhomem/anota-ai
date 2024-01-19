package com.kipper.anotaai.domain.product;

import com.kipper.anotaai.domain.category.Category;

public record ProductDTO(String title, String description, String ownerId, Integer price, String categoryId) {}
