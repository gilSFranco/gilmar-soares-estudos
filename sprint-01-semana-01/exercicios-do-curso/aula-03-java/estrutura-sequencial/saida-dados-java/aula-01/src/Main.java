import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);

        String nome = "Maria";
        int x = 10, idade = 31;
        double y = 10.35784, renda = 4000.0;

        System.out.println(x);
        System.out.println(y);
        System.out.printf("%.2f%n", y);
        System.out.printf("%.4f%n", y);

        System.out.println("Resultado = " + x + " metros!");

        System.out.printf("Resultado = %.2f metros! %n", y);

        System.out.printf("%s tem %d anos e ganha R$ %.2f! %n", nome, idade, renda);
    }
}