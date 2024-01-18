package com.kipper.anotaai.services;

import com.kipper.anotaai.domain.category.Category;
import com.kipper.anotaai.domain.category.CategoryDTO;
import com.kipper.anotaai.domain.category.exceptions.CategoryNotFoundException;
import com.kipper.anotaai.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private CategoryRepository repository;

    private CategoryService(CategoryRepository repository){
        this.repository = repository;
    }

    public List<Category> getAll() {
        return repository.findAll();
    }

    public Category insert(CategoryDTO data){
        Category newCat = new Category(data);
        this.repository.save(newCat);
        return newCat;
    }

    public Category update(String id, CategoryDTO data) {
        Category cat = this.repository.findById(id).orElseThrow(CategoryNotFoundException::new);

        if(!data.title().isEmpty()) cat.setTitle(data.title());
        if(!data.description().isEmpty()) cat.setDescription(data.title());

        repository.save(cat);
        return cat;
    }

    public void delete(String id) {
        Category cat = this.repository.findById(id).orElseThrow(CategoryNotFoundException::new);

        repository.delete(cat);
    }
}
