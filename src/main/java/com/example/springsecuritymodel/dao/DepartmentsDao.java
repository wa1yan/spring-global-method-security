package com.example.springsecuritymodel.dao;

import com.example.springsecuritymodel.entities.Department;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentsDao extends CrudRepository<Department,Integer> {
}
