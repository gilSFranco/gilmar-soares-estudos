public class Main {
    public static void main(String[] args) {
        double x, y, z, A, B, C;

        x = 3.0;
        y = 4.0;
        z = -5.0;

        A = Math.sqrt(x);
        B = Math.sqrt(y);
        C = Math.sqrt(25.0);
        System.out.printf("Raiz quadrada de %.1f = %f%n", x, A);
        System.out.printf("Raiz quadrada de %.1f = %.1f%n", y, B);
        System.out.printf("Raiz quadrada de 25 = %.1f%n", C);

        A = Math.pow(x, y);
        B = Math.pow(x, 2.0);
        C = Math.pow(5.0, 2.0);
        System.out.printf("%.1f elevado a %.1f = %.1f. %n", x, y, A);
        System.out.printf("%.1f elevado ao quadrado = %.1f. %n", x, B);
        System.out.printf("5 elevado ao quadrado = %.1f. %n", C);

        A = Math.abs(y);
        B = Math.abs(z);
        System.out.printf("Valor absoluto de %.1f = %.1f. %n", y, A);
        System.out.printf("Valor absoluto de %.1f = %.1f. %n", z, B);

    }
}