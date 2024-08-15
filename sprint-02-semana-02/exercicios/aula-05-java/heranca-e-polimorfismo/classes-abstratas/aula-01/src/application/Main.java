package application;

import entities.Account;
import entities.BusinessAccount;
import entities.SavingsAccount;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Account> list = new ArrayList<>();

        double sum = 0.0;

        Account account2 = new SavingsAccount(1002, "Maria", 500.0, 0.01);
        Account account4 = new SavingsAccount(1004, "John", 1000.0, 400.00);

        Account account3 = new BusinessAccount(1003, "Bob", 300.0, 0.01 );
        Account account5 = new BusinessAccount(1005, "Anna", 500.0, 500.00);

        list.add(account2);
        list.add(account3);
        list.add(account4);
        list.add(account5);

        for (Account account : list) {
            sum += account.getBalance();
        }

        System.out.printf("Total balance: %.2f.%n", sum);

        for (Account account : list) {
            account.deposit(10.0);
        }

        for (Account account : list) {
            System.out.printf("Update balance for account %d: %.2f.%n", account.getNumber(), account.getBalance());
        }
    }
}