package application;

import entities.Employee;
import entities.OutsourcedEmployee;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        List<Employee> list = new ArrayList<>();

        int numberEmployees;

        System.out.print("Enter the number of employees: ");
        numberEmployees = sc.nextInt();

        for(int i = 0; i < numberEmployees; i++) {
            String name;
            int hours;
            double valuePerHour, additionalCharge;
            char isEmployee;

            System.out.println("Employee #" + (i + 1) + " data: ");
            System.out.print("Outsourced (y/n)? ");
            isEmployee = sc.next().charAt(0);

            sc.nextLine();

            System.out.print("Name: ");
            name = sc.nextLine();

            System.out.print("Hours: ");
            hours = sc.nextInt();

            System.out.print("Value per hour: ");
            valuePerHour = sc.nextDouble();

            if(isEmployee == 'y') {
                System.out.print("Additional charge: ");
                additionalCharge = sc.nextDouble();

                list.add(new OutsourcedEmployee(name, hours, valuePerHour, additionalCharge));
            } else{
                list.add(new Employee(name, hours, valuePerHour));
            }
        }

        System.out.println();
        System.out.println("PAYMENTS: ");

        for(Employee employee : list) {
            System.out.println(employee.getName() + " - $ " + String.format("%.2f", employee.payment()) + "!");
        }

        sc.close();
    }
}