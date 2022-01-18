package com.example.springsecuritymodel.dao;

import com.example.springsecuritymodel.entities.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomersDao extends CrudRepository<Customer,Integer> {
}
