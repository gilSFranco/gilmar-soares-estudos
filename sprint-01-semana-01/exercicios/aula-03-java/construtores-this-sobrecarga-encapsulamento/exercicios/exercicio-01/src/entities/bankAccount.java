package entities;

public class bankAccount {
    private int numberAccount;
    private String ownerName;
    private double balance;

    public bankAccount(int numberAccount, String ownerName, double initialDeposit) {
        this.numberAccount = numberAccount;
        this.ownerName = ownerName;
        deposit(initialDeposit);
    }

    public bankAccount(int numberAccount, String ownerName) {
        this.numberAccount = numberAccount;
        this.ownerName = ownerName;
    }

    public int getNumberAccount() {
        return numberAccount;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public void withdraw(double amount) {
        double amountMoreTax = amount + 5.00;
        balance -= amountMoreTax;
    }

    public String toString() {
        return "Account " + getNumberAccount() + ", Holder: " + getOwnerName() + ", Balance: R$ " + String.format("%.2f", balance) + "!";
    }
}
