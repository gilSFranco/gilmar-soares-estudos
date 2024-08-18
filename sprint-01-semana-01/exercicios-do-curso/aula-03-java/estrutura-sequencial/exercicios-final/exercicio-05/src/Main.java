import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int codigoPeca1, quantidadePeca1, codigoPeca2, quantidadePeca2;
        double precoPeca1, precoPeca2, totalCompra;

        System.out.print("Digite primeiro qual é o codigo da peça, depois a quantidade e por fim qual é o preço de cada(separados por espaços): ");
        codigoPeca1 = sc.nextInt();
        quantidadePeca1 = sc.nextInt();
        precoPeca1 = sc.nextDouble();

        System.out.print("Digite primeiro qual é o codigo da peça, depois a quantidade e por fim qual é o preço de cada(separados por espaços): ");
        codigoPeca2 = sc.nextInt();
        quantidadePeca2 = sc.nextInt();
        precoPeca2 = sc.nextDouble();

        totalCompra = quantidadePeca1 * precoPeca1 + quantidadePeca2 * precoPeca2;

        System.out.printf("Valor total a pagar pelas peças de codigo %d e %d compradas: %.2f.%n", codigoPeca1, codigoPeca2, totalCompra);
    }
}