package model.dao;

import model.entities.Employee;

import java.util.List;

public interface EmployeeDao {
    void insert(Employee employee);
    List<Employee> findAll();
}
