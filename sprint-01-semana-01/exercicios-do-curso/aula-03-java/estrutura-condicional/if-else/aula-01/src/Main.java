import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int hora, x = 5, y = 10;

        //estrutura condicional simples
        if(x == 5){
            System.out.println("O x é igual a 5");
        }

        //estrutura condicional composta
        if(y > 5){
            System.out.println("O y é maior que 5!");
        } else{
            System.out.println("O y não é maior que 5!");
        }

        System.out.println("Que horas são? ");
        hora = sc.nextInt();

        //estrutura condicional composta - if, else if e else
        if(hora < 12){
            System.out.println("Bom dia!");
        } else if(hora < 18){
            System.out.println("Boa tarde!");
        } else{
            System.out.println("Boa noite!");
        }

        sc.close();
    }
}