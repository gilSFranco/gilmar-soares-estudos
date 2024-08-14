package application;

import entities.Employee;

import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        Employee employee = new Employee();

        double percentage;

        System.out.print("Name: ");
        employee.name = sc.nextLine();

        System.out.print("Gross salary: ");
        employee.grossSalary = sc.nextDouble();

        System.out.print("Tax: ");
        employee.tax = sc.nextDouble();

        System.out.println("\nEmployee: " + employee);

        System.out.print("Which percentage to increase salary? ");
        percentage = sc.nextDouble();

        employee.increaseSalary(percentage);

        System.out.print("\nUpdate data: " + employee);
    }
}