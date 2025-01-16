package com.example.ecommerce.service;

import com.example.ecommerce.product_entity.product_details;

import java.util.List;
import java.util.Optional;

public interface ecommservice {
    void save(product_details temp);

    List<product_details> findAll();

    Optional<product_details> findbyProductName(String name);

    float getPrice(String name);

}
