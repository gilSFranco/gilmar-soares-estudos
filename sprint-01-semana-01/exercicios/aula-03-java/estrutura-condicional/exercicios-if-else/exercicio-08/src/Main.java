import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        double salario, salarioPosImposto, imposto, diferenca;

        System.out.println("Quanto você recebe por mês? ");
        salario = sc.nextDouble();

        if(salario >= 0.0 && salario <= 2000.0){
            System.out.printf("Você recebe R$ %.2f, portanto, está isento de impostos.", salario);
        } else if(salario > 2000.0 && salario <= 3000.0){
            diferenca = salario - 2000.0;
            imposto = diferenca * 8 / 100;
            salarioPosImposto = salario - imposto;

            System.out.printf("Salário - R$ %.2f.%nImposto - R$ %.2f.%nSalário pós imposto - R$ %.2f.", salario, imposto, salarioPosImposto);
        } else if(salario > 3000.0 && salario <= 4500.0){
            diferenca = salario - 3000.0;
            imposto = ((double) 1000 * 8 / 100) + (diferenca * 18 / 100);
            salarioPosImposto = salario - imposto;

            System.out.printf("Salário - R$ %.2f.%nImposto - R$ %.2f.%nSalário pós imposto - R$ %.2f.", salario, imposto, salarioPosImposto);
        } else if(salario > 4500.0){
            diferenca = salario - 4500.0;
            imposto = ((double) 1000 * 8 / 100) + ((double) 1500 * 18 / 100) + (diferenca * 28 / 100);
            salarioPosImposto = salario - imposto;

            System.out.printf("Salário - R$ %.2f.%nImposto - R$ %.2f.%nSalário pós imposto - R$ %.2f.", salario, imposto, salarioPosImposto);
        }
    }
}