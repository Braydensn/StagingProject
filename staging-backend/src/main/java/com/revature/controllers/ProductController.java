package com.revature.controllers;

import com.revature.models.Product;
import com.revature.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getProducts(){
        return ResponseEntity.ok(productService.getProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") Integer id){
        Optional<Product> product = productService.getProduct(id);
        if(product.isPresent()){
            return ResponseEntity.ok(product.get());
        }
        return ResponseEntity.badRequest().build();
    }
}
