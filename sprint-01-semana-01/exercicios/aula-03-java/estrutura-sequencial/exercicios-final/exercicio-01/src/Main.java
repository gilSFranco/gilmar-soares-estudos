import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int numero1, numero2, soma;

        System.out.println("Digite o primeiro número: ");
        numero1 = sc.nextInt();

        System.out.println("Digite o segundo número: ");
        numero2 = sc.nextInt();

        soma = numero1 + numero2;

        System.out.printf("A soma dos números digitados é de: %d + %d = %d!", numero1, numero2, soma);
    }
}