package com.revature.services;

import com.revature.models.Product;
import com.revature.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private ProductRepository productRepo;

    @Autowired
    public ProductService(ProductRepository productRepo){
        this.productRepo = productRepo;
    }

    public List<Product> getProducts(){
        return productRepo.findAll();
    }

    public Optional<Product> getProduct(Integer id){
        return productRepo.findById(id);
    }
}
