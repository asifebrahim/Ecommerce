package com.example.ecommerce.service;


import com.example.ecommerce.controller.dao.ecomm;
import com.example.ecommerce.product_entity.product_details;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ecommserviceImpl implements ecommservice{
    private ecomm Ecomm;
    @Autowired
    public ecommserviceImpl(ecomm Ecomm){
        this.Ecomm=Ecomm;
    }
    @Override
    public List<product_details> findAll(){
        return Ecomm.findAll();
    }
    @Override
    public Optional<product_details> findbyProductName(String name){
        int tempId=Ecomm.findByProductName(name);
        Optional<product_details> product=Ecomm.findById(tempId);
        return product;
    }
    @Override
    public float getPrice(String name){
        int tempId=Ecomm.findByProductName(name);
        Optional<product_details> product=Ecomm.findById(tempId);
        return product.get().getProductPrice();
    }

    @Override
    public void save(product_details tempProduct){
        Ecomm.save(tempProduct);
    }
}
