import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        double[] vect = new double[3];
        double soma = 0, media;
        int numero;

        System.out.print("Digite um número: ");
        numero = sc.nextInt();

        for(int i = 0; i < numero; i++){
            vect[i] = sc.nextDouble();
            soma += vect[i];

            System.out.println(vect[i]);
        }

        media = soma / numero;

        System.out.printf("Average height: %.2f%n", media);

        sc.close();
    }
}