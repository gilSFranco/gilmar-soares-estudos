package util;

public class CurrencyConverter {
    public static double dollarsToReais(double dollarPrice, double quantityDollars) {
        double tax = dollarPrice * 6 / 100;
        return (dollarPrice + tax) * quantityDollars;
    }
}
