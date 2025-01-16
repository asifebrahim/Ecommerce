package com.example.ecommerce.service;

import com.example.ecommerce.controller.dao.employee;
import com.example.ecommerce.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class employeeServiceImpl implements employeeService{
    private employee Employee;
    @Autowired
    public employeeServiceImpl(employee Employee){
        this.Employee=Employee;
    }

    @Override
    public void save(Person thePerson){
        Employee.save(thePerson);
    }
}
