import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        double numero;

        System.out.println("Digite um numero: ");
        numero = sc.nextDouble();

        if(numero >= 0.0 && numero <= 25.0){
            System.out.printf("O numero %.1f está entre o intervalo [0,25].%n", numero);
        } else if(numero > 25.0 && numero <= 50.0){
            System.out.printf("O numero %.1f está entre o intervalo [25,50].%n", numero);
        } else if(numero > 50.0 && numero <= 75.0){
            System.out.printf("O numero %.1f está entre o intervalo [50,75].%n", numero);
        } else if(numero > 75.0 && numero <= 100.0){
            System.out.printf("O numero %.1f está entre o intervalo [75,100].%n", numero);
        } else{
            System.out.printf("O numero %.1f está fora do intervalo.%n", numero);
        }
    }
}