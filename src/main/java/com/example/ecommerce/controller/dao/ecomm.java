package com.example.ecommerce.controller.dao;

import com.example.ecommerce.product_entity.product_details;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ecomm extends JpaRepository<product_details,Integer> {
    Integer findByProductName(String productName);


}
