package application;

import model.dao.DaoFactory;
import model.dao.EmployeeDao;
import model.entities.Employee;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EmployeeDao employeeDao = DaoFactory.createEmployeeDao();
        List<Employee> lista = new ArrayList<>();

        System.out.println("\n===== TEST 1: Employee Insert =====");
        Employee employee = new Employee(null, "Lauany", 3000.0);
        employeeDao.insert(employee);



        System.out.println("\n===== TEST 2: Employee FindAll =====");
        lista = employeeDao.findAll();

        for (Employee emp : lista) {
            System.out.println(emp);
        }
    }
}