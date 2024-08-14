package application;

import entities.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        Integer positionEmployee;
        int numberEmployees, idEmployee;
        double percentage;

        List<Employee> listId = new ArrayList<>();

        System.out.print("How many employees will be registered? ");
        numberEmployees = sc.nextInt();

        for(int i = 0; i < numberEmployees; i++) {
            int id;
            String name;
            double salary;

            System.out.printf("\nEmployee #%d%n", (i + 1));

            System.out.print("Id: ");
            id = sc.nextInt();

            sc.nextLine();

            System.out.print("Name: ");
            name = sc.nextLine();

            System.out.print("Salary: ");
            salary = sc.nextDouble();

            Employee employee = new Employee(id, name, salary);

            listId.add(employee);
        }

        System.out.print("\nEnter the employee id that will have salary increase: ");
        idEmployee = sc.nextInt();

        positionEmployee = position(listId, idEmployee);

        if(positionEmployee == null) {
            System.out.println("This id does not exist.");
        } else {
            System.out.print("\nEnter the percentage: ");
            percentage = sc.nextDouble();

            listId.get(positionEmployee).increaseSalary(percentage);
        }

        System.out.println("\nList of employees: ");

        for(Employee employee : listId) {
            System.out.println(employee);
        }

        sc.close();
    }

    public static Integer position(List<Employee> list, int idEmployee) {
        for(int i = 0; i < list.size(); i++) {
            if(list.get(i).getId() == idEmployee) {
                return i;
            }
        }
        return null;
    }
}