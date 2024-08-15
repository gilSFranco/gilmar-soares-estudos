package application;

import entities.Department;
import entities.HourContract;
import entities.Worker;
import enums.WorkerLevel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ParseException {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        String departmentName, workerName, workerLevel, monthAndYear;
        int numberContracts, hours, month, year;
        double baseSalary, valuePerHour;
        Date contractDate;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        System.out.print("Enter the partment name: ");
        departmentName = sc.nextLine();

        System.out.println("\nEnter worker data: ");
        System.out.print("Name: ");
        workerName = sc.nextLine();

        System.out.print("Level: ");
        workerLevel = sc.nextLine();

        System.out.print("Base salary: ");
        baseSalary = sc.nextDouble();

        Worker worker = new Worker(workerName, WorkerLevel.valueOf(workerLevel), baseSalary, new Department(departmentName));

        System.out.print("\nHow many contracts to this worker? ");
        numberContracts = sc.nextInt();

        for(int i = 1; i <= numberContracts; i++) {
            System.out.println("\nEnter contract #" + i + " data: ");
            System.out.print("Date (DD/MM/YYYY): ");
            contractDate = sdf.parse(sc.next());

            System.out.print("Value per hour: ");
            valuePerHour = sc.nextDouble();

            System.out.print("Duration (hours): ");
            hours = sc.nextInt();

            HourContract contract = new HourContract(contractDate, valuePerHour, hours);
            worker.addContract(contract);
        }

        System.out.println();

        System.out.print("Enter month and year to calculate income (MM/YYYY): ");
        monthAndYear = sc.next();

        month = Integer.parseInt(monthAndYear.substring(0,2));
        year = Integer.parseInt(monthAndYear.substring(3));

        System.out.printf("\nName: %s.%n", worker.getName());
        System.out.printf("Department: %s.%n", worker.getDepartment().getName());
        System.out.printf("Income for %s: %.2f.%n", monthAndYear, worker.income(year, month));

        sc.close();
    }
}