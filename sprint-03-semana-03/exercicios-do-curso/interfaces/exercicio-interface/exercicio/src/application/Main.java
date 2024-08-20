package application;

import model.entities.Contract;
import model.entities.Installment;
import model.services.ContractService;
import model.services.PaypalService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        int number, months;
        LocalDate date;
        double totalValue;

        System.out.println("Entre com os dados do contrato: ");
        System.out.print("Numero: ");
        number = sc.nextInt();

        System.out.print("Data (dd/MM/yyyy): ");
        date = LocalDate.parse(sc.next(), fmt);

        System.out.print("Valor do contrato: ");
        totalValue = sc.nextDouble();

        System.out.print("Entre com o numero de parcelas: ");
        months = sc.nextInt();

        Contract contract = new Contract(number, date, totalValue);
        ContractService contractService = new ContractService(new PaypalService());

        contractService.processContract(contract, months);

        for(Installment installment : contract.getInstallment()) {
            System.out.println(installment);
        }

        sc.close();
    }
}