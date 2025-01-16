package com.example.ecommerce.controller.dao;

import com.example.ecommerce.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface employee extends JpaRepository<Person,Integer> {

}
