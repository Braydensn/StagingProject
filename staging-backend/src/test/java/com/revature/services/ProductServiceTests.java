package com.revature.services;

import com.revature.models.Product;
import com.revature.repositories.ProductRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTests {

    public ProductService sut;

    @Mock
    ProductRepository repoMock;

    @BeforeAll
    public static void beforeAll() {
        System.out.println("Starting ProductService tests...");
    }

    @AfterAll
    public static void afterAll() {
        System.out.println("ProductService Tests complete.");
    }

    @BeforeEach
    public void beforeEach(){
        sut = new ProductService(repoMock);
    }

    @Test
    public void testGetProducts(){
        List<Product> productList = new ArrayList();
        productList.add(new Product(1, "some Product", "it's a product", "product.png", 10.50));
        productList.add(new Product(2, "another Product", "yep, it's a product", "product.png", 15));
        productList.add(new Product(3, "yet anotherProduct", "you guessed it, it's a product", "product.png", 32.99));

        Mockito.when(repoMock.findAll()).thenReturn(productList);

        List<Product> result = sut.getProducts();

        Assertions.assertEquals(result, productList);
    }

    @Test
    public void testGetProductValidId(){
        Product returnedProduct = new Product(1, "some Product", "it's a product", "product.png", 10.50);

        Mockito.when(repoMock.findById(1)).thenReturn(Optional.of(returnedProduct));

        Optional<Product> result = sut.getProduct(1);

        Assertions.assertEquals(result, Optional.of(returnedProduct));
    }

    @Test
    public void testGetProductInvalidId(){
        Mockito.when(repoMock.findById(2)).thenReturn(Optional.empty());

        Optional<Product> result = sut.getProduct(2);

        Assertions.assertTrue(result.isEmpty());
    }

}
