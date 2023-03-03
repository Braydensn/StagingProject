package com.revature.controllers;

import com.revature.models.Product;
import com.revature.services.ProductService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTests {
    ProductController sut;

    @Mock
    ProductService serviceMock;

    @BeforeAll
    public static void beforeAll() {
        System.out.println("Starting ProductController tests...");
    }

    @AfterAll
    public static void afterAll() {
        System.out.println("ProductController Tests complete.");
    }

    @BeforeEach
    public void beforeEach(){
        sut = new ProductController(serviceMock);
    }

    @Test
    public void testGetProducts(){
        List<Product> productList = new ArrayList();
        productList.add(new Product(1, "some Product", "it's a product", "product.png", 10.50));
        productList.add(new Product(2, "another Product", "yep, it's a product", "product.png", 15));
        productList.add(new Product(3, "yet anotherProduct", "you guessed it, it's a product", "product.png", 32.99));

        Mockito.when(serviceMock.getProducts()).thenReturn(productList);

        ResponseEntity<List<Product>> result = sut.getProducts();

        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        Assertions.assertEquals(productList, result.getBody());
    }

    @Test
    public void testGetProductValidId(){
        Product product = new Product(1, "test Product", "it's a product", "product.png", 10.50);

        Mockito.when(serviceMock.getProduct(1)).thenReturn(Optional.of(product));

        ResponseEntity<Product> result = sut.getProduct(1);

        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
        Assertions.assertEquals(product, result.getBody());
    }

    @Test
    public void testGetProductInvalidId(){
        Mockito.when(serviceMock.getProduct(2)).thenReturn(Optional.empty());

        ResponseEntity<Product> result = sut.getProduct(2);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }

}
