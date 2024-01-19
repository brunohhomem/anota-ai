package com.kipper.anotaai.controllers;

import com.kipper.anotaai.domain.product.Product;
import com.kipper.anotaai.domain.product.ProductDTO;
import com.kipper.anotaai.services.ProductService;
import jakarta.websocket.server.PathParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private ProductService service;

    public ProductController(ProductService service){
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAll(){
        List<Product> products = this.service.getAll();
        return ResponseEntity.ok().body(products);
    }

    @PostMapping
    public ResponseEntity<Product> insert(@RequestBody ProductDTO data){
        Product newProduct = this.service.insert(data);
        return ResponseEntity.ok().body(newProduct);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathParam("id") String id, @RequestBody ProductDTO data){
        Product updateProduct = this.service.update(id,data);
        return ResponseEntity.ok().body(updateProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> delete(@PathParam("id") String id){
        this.service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
