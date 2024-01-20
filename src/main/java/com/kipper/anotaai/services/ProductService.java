package com.kipper.anotaai.services;

import com.kipper.anotaai.domain.category.Category;
import com.kipper.anotaai.domain.category.exceptions.CategoryNotFoundException;
import com.kipper.anotaai.domain.product.Product;
import com.kipper.anotaai.domain.product.ProductDTO;
import com.kipper.anotaai.domain.product.exceptions.ProductNotFoundException;
import com.kipper.anotaai.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private ProductRepository repository;
    private CategoryService categoryService;

    private ProductService(CategoryService categoryService,ProductRepository repository){
        this.categoryService = categoryService;
        this.repository = repository;
    }

    public List<Product> getAll() {
        return repository.findAll();
    }

    public Product insert(ProductDTO data){
        Category category = this.categoryService.getById(data.categoryId()).orElseThrow(CategoryNotFoundException::new);

        Product newProduct = new Product(data);
        newProduct.setCategory(category);
        this.repository.save(newProduct);
        return newProduct;
    }

    public Product update(String id, ProductDTO data) {
        Product product = this.repository.findById(id).orElseThrow(ProductNotFoundException::new);

        if(data.categoryId() != null){
            this.categoryService.getById(data.categoryId()).ifPresent(product::setCategory);
        }

        if(!data.title().isEmpty()) product.setTitle(data.title());
        if(!data.description().isEmpty()) product.setDescription(data.description());
        if(!(data.price() == null)) product.setPrice(data.price());

        repository.save(product);
        return product;
    }

    public void delete(String id) {
        Product product = this.repository.findById(id).orElseThrow(ProductNotFoundException::new);

        repository.delete(product);
    }
}
