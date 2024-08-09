import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int numberId, horasTrabalhadas;
        double valorPorHora, salario;

        System.out.print("Qual seu numero de indentificação? ");
        numberId = sc.nextInt();

        System.out.print("Quantas horas voce trabalhou esse mes? ");
        horasTrabalhadas = sc.nextInt();

        System.out.print("Quanto voce recebe por hora trabalhada? ");
        valorPorHora = sc.nextDouble();

        salario = valorPorHora * horasTrabalhadas;

        System.out.printf("Funcionario: %d.%nTrabalhou %d horas no mes.%nPortanto, seu salário é de: R$ %.2f!%n", numberId, horasTrabalhadas, salario);
    }
}