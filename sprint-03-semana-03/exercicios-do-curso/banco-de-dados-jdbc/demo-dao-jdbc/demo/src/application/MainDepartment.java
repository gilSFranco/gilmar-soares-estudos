package application;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainDepartment {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        DepartmentDao departmentDao = DaoFactory.createDepartmentDao();

        int id;
        List<Department> lista = new ArrayList<>();

        System.out.println("===== TEST 1: department findById =====");
        Department department = departmentDao.findById(3);
        System.out.println(department);

        System.out.println();

        System.out.println("===== TEST 2: department findAll =====");
        lista = departmentDao.findAll();
        lista.stream().forEach(System.out::println);

        System.out.println();

        System.out.println("===== TEST 3: department insert =====");
        Department newDepartment = new Department(null, "Adalberto");
        departmentDao.insert(newDepartment);

        System.out.println("Inserted! New id = " + newDepartment.getId());

        System.out.println();

        System.out.println("===== TEST 4: department update =====");
        department = departmentDao.findById(2);
        department.setName("Notebooks");
        departmentDao.update(department);

        System.out.println("Updated!");

        System.out.println();

        System.out.println("===== TEST 5: department delete =====");
        System.out.println("Digite um id para deletar um departamento: ");
        id = sc.nextInt();
        departmentDao.deleteById(id);

        System.out.println("Deleted!");

        System.out.println();

        sc.close();
    }
}
