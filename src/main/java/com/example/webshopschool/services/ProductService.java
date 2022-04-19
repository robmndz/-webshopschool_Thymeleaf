package com.example.webshopschool.services;

import com.example.webshopschool.model.Product;
import com.example.webshopschool.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    public final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public String addNewProduct(String productName, String productNumber) {
        productRepository.save(new Product(productName, productNumber));
        return productName + " is saved";
    }

    public Iterable<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long productId) {
        if (productRepository.findById(productId).isPresent())
            return productRepository.findById(productId).get();
        else return null;
    }
}
