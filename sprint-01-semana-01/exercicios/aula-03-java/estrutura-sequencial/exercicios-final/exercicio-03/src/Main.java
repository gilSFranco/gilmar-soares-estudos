import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int a, b, c, d, diferenca;

        System.out.println("Digite um valor para a: ");
        a = sc.nextInt();

        System.out.println("Digite um valor para b: ");
        b = sc.nextInt();

        System.out.println("Digite um valor para c: ");
        c = sc.nextInt();

        System.out.println("Digite um valor para d: ");
        d = sc.nextInt();

        diferenca = (a * b - c * d);

        System.out.printf("A diferença do produto de %d(a) e %d(b) e de %d(c) e %d(d) é de: %d", a, b, c, d, diferenca);
    }
}