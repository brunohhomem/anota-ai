package com.kipper.anotaai.controllers;

import com.kipper.anotaai.domain.category.Category;
import com.kipper.anotaai.domain.category.CategoryDTO;
import com.kipper.anotaai.services.CategoryService;
import jakarta.websocket.server.PathParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private CategoryService service;

    public CategoryController(CategoryService service){
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Category>> getAll(){
        List<Category> categories = this.service.getAll();
        return ResponseEntity.ok().body(categories);
    }

    @PostMapping
    public ResponseEntity<Category> insert(@RequestBody CategoryDTO data){
        Category newCat = this.service.insert(data);
        return ResponseEntity.ok().body(newCat);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> update(@PathParam("id") String id, @RequestBody CategoryDTO data){
        Category updateCat = this.service.update(id,data);
        return ResponseEntity.ok().body(updateCat);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Category> delete(@PathParam("id") String id){
        this.service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
