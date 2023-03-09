package com.revature.controllers;

import com.revature.models.Product;
import com.revature.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * This class handles HTTP requests sent to the /product endpoint
 */
@RestController
@RequestMapping("/product")
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:3000"}, allowCredentials = "true", exposedHeaders = "Authorization")
public class ProductController {

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService){
        this.productService = productService;
    }

    /**
     * Retrieves all products from the database and returns a list of them to the client
     * @return A response entity with the list of all products
     */
    @GetMapping
    public ResponseEntity<List<Product>> getProducts(){
        return ResponseEntity.ok(productService.getProducts());
    }

    /**
     * Retrieves a specific product to be sent back to the client
     * @param id The ID of the desired product
     * @return A response entity with the desired product, or nothing if the product was not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") Integer id){
        Optional<Product> product = productService.getProduct(id);
        if(product.isPresent()){
            return ResponseEntity.ok(product.get());
        }
        return ResponseEntity.badRequest().build();
    }
}
