import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int codigo, quantidade;
        double conta;

        System.out.println("Qual o codigo da comida? ");
        codigo = sc.nextInt();

        System.out.println("E qual a quantidade de comida? ");
        quantidade = sc.nextInt();

        if(codigo == 1){
            conta = quantidade * 4.00;
            System.out.printf("Voce comprou %d Cachorro(s) Quente(s) e o valor final é de: %.2f.%n", quantidade, conta);
        } else if(codigo == 2){
            conta = quantidade * 4.50;
            System.out.printf("Voce comprou %d X-Salada(s) e o valor final é de: %.2f.%n", quantidade, conta);
        } else if(codigo == 3){
            conta = quantidade * 5.00;
            System.out.printf("Voce comprou %d X-Bacon(s) e o valor final é de: %.2f.%n", quantidade, conta);
        } else if(codigo == 4){
            conta = quantidade * 2.00;
            System.out.printf("Voce comprou %d Torrada(s) Simples e o valor final é de: %.2f.%n", quantidade, conta);
        } else if(codigo == 5){
            conta = quantidade * 1.50;
            System.out.printf("Voce comprou %d Refrigerante(s) e o valor final é de: %.2f.%n", quantidade, conta);
        } else{
            System.out.println("Digite um valor válido.");
        }
    }
}