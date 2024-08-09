import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        double raio, area;

        System.out.println("Digite o valor do raio: ");
        raio = sc.nextDouble();

        area = 3.14159 * (Math.pow(raio, 2));

        System.out.printf("O circulo tem %.2f de raio, portanto, sua area é de: %.4f!", raio, area);
    }
}