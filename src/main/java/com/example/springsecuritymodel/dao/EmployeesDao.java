package com.example.springsecuritymodel.dao;

import com.example.springsecuritymodel.entities.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeesDao extends CrudRepository<Employee,Integer> {
}
