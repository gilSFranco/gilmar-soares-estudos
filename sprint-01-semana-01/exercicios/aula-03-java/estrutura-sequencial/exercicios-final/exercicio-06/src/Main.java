import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        double a, b, c, areaTriangulo, areaCirculo, areaTrapezio, areaQuadrado, areaRetangulo;

        System.out.println("Digite o valor para a, b e c respectivamente (dando espaços entre eles): ");
        a = sc.nextDouble();
        b = sc.nextDouble();
        c = sc.nextDouble();

        areaTriangulo = a * c / 2;
        areaCirculo = 3.14159 * (Math.pow(c, 2));
        areaTrapezio = ((a + b) * c) / 2;
        areaQuadrado = Math.pow(b, 2);
        areaRetangulo = a * b;

        System.out.printf("Triangulo: %.3f.%n", areaTriangulo);
        System.out.printf("Circulo: %.3f.%n", areaCirculo);
        System.out.printf("Trapezio: %.3f.%n", areaTrapezio);
        System.out.printf("Quadrado: %.3f.%n", areaQuadrado);
        System.out.printf("Retangulo: %.3f.%n", areaRetangulo);
    }
}