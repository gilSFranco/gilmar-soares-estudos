package application;

import entities.bankAccount;

import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        String ownerName;
        int numberAccount;
        double balance;
        char response;

        bankAccount account;

        System.out.print("Enter account number: ");
        numberAccount = sc.nextInt();

        sc.nextLine();

        System.out.print("Enter account holder: ");
        ownerName = sc.nextLine();

        System.out.print("Is there any initial deposit (y/n)? ");
        response = sc.next().charAt(0);

        if(response == 'y') {
            System.out.print("Enter initial deposit value: ");
            balance = sc.nextDouble();

            account = new bankAccount(numberAccount, ownerName, balance);
        } else{
            account = new bankAccount(numberAccount, ownerName);
        }

        System.out.println("\nAccount data: ");
        System.out.println(account);

        System.out.print("\nEnter a deposit value: ");
        balance = sc.nextDouble();
        account.deposit(balance);

        System.out.println("\nUpdated account data: ");
        System.out.println(account);

        System.out.print("\nEnter a withdraw value: ");
        balance = sc.nextDouble();
        account.withdraw(balance);

        System.out.println("\nUpdated account data: ");
        System.out.println(account);
    }
}