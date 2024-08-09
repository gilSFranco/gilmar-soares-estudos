import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int numero;

        System.out.println("Digite um numero: ");
        numero = sc.nextInt();

        if(numero < 0){
            System.out.printf("%d é negativo.%n", numero);
        } else{
            System.out.printf("%d é positivo.%n", numero);
        }
    }
}