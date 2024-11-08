package com.example.API_Product.controller;

import com.example.API_Product.model.Product;
import com.example.API_Product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    public ProductController() {
    }


    @GetMapping("/products")
    @ResponseBody
    public List<Product> getProductList() {
        return productService.findAll();
    }

 
    @GetMapping("/products/{id}")
    @ResponseBody
    public ResponseEntity<Product> getProductById(@PathVariable("id") int productId) {
        for (Product product : productService.findAll()) {
            if (product.getProductId() == productId) {
                return ResponseEntity.status(200).body(product);
            }
        }
        return ResponseEntity.status(404).body(null);
    }


    @DeleteMapping("/products/{id}")
    @ResponseBody
    public List<Product> removeProductById(@PathVariable("id") Long productId) {
        productService.delete(productId);
        return productService.findAll();
    }

    
    @PostMapping("/products")
    @ResponseBody
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        productService.save(product);
        return ResponseEntity.status(201).body(product);
    }

    
    @PutMapping("/products/{id}")
    @ResponseBody
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Long productId, @RequestBody Product updateProduct) {
        Product product = productService.findById(productId);
        if (product != null) {
            product.setProductName(updateProduct.getProductName());
            product.setDescription(updateProduct.getDescription());
            product.setPrice(updateProduct.getPrice());
            product.setStock(updateProduct.getStock());
            product.setCategoryId(updateProduct.getCategoryId());
            product.setImageUrl(updateProduct.getImageUrl());
            productService.save(product);
            return ResponseEntity.status(200).body(product);
        }
        return ResponseEntity.status(404).body(null);
    }
}

