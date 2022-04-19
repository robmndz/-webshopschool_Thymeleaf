package com.example.webshopschool.repository;

import com.example.webshopschool.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}
