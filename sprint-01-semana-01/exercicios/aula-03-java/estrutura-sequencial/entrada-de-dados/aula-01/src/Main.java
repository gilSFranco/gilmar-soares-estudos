import java.util.Scanner;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        String nome, s1, s2, s3;
        char sexo, a;
        int idade, b, d;
        double salario, c;

        System.out.println("Digite até não querer mais e pressione enter: ");
        d = sc.nextInt();
        sc.nextLine();
        s1 = sc.nextLine();
        s2 = sc.nextLine();
        s3 = sc.nextLine();
        
        System.out.println(d);
        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);

        System.out.print("Digite o nome do aluno: ");
        nome = sc.next();

        System.out.print("Digite a idade do aluno: ");
        idade = sc.nextInt();

        System.out.print("Digite o salário do aluno: ");
        salario = sc.nextDouble();

        System.out.print("Digite o sexo do aluno: ");
        sexo = sc.next().charAt(0);

        System.out.println("Digite vários dados separados por espaço: ");
        a = sc.next().charAt(0);
        b = sc.nextInt();
        c = sc.nextDouble();

        System.out.printf("%s tem %d anos e é do sexo %s! E recebe R$ %.2f por mes.%n", nome, idade, sexo, salario);
        System.out.printf("%s, %d, %.2f.%n", a, b, c);

        sc.close();
    }
}