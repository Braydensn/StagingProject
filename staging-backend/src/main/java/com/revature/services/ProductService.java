package com.revature.services;

import com.revature.models.Product;
import com.revature.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Contains additional logic required to handle requests related to products
 */
@Service
public class ProductService {

    private ProductRepository productRepo;

    @Autowired
    public ProductService(ProductRepository productRepo){
        this.productRepo = productRepo;
    }

    /**
     * Retrieves all products from the database
     * @return A list of all of the products
     */
    public List<Product> getProducts(){
        return productRepo.findAll();
    }

    /**
     * Retrieves a specific product from the database
     * @param id The ID of the desired product
     * @return An optional that contains the product if found
     */
    public Optional<Product> getProduct(Integer id){
        return productRepo.findById(id);
    }
}
