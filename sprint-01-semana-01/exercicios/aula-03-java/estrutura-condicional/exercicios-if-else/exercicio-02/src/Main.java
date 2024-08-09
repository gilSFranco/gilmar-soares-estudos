import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int numero;

        System.out.println("Digite um numero: ");
        numero = sc.nextInt();

        if(numero % 2 == 0){
            System.out.printf("O número %d é par.%n", numero);
        } else{
            System.out.printf("O número %d é impar.%n", numero);
        }
    }
}