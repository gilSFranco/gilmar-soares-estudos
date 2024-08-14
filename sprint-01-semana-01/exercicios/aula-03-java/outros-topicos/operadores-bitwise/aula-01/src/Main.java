import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n1 = 89, n2 = 60;
        int numero, mask = 0b100000;

        System.out.println("Digite um numero: ");
        numero = sc.nextInt();

        System.out.println(numero & mask);

        //0101 1001
        //0010 0000

        //0000 0000

        //0111 1001
        //0010 0000

        //0010 0000

        if((numero & mask) != 0){
            System.out.println("6th bit is true!");
        } else{
            System.out.println("6th bit is false!");
        }

        //Operadores bitwise - &(E bit a bit), |(OU bit a bit) e ^(OU EXCLUSIVO bit a bit)

        /*
        O & funciona como o &&, porém bit a bit,
        ou seja:
        (89) 0101 1001
        (60) 0011 1100

        O operador & vai comparar bit a bit dos dois números,
        se os dois forem 1, o resultado é 1, se não é 0. Por isso,
        que dessa comparação surge o número 24 que é assim em
        binário:

        0001 1000
         */
        System.out.println(n1 & n2);

        /*
        O | funciona da mesma forma, porém se apenas um dos dois
        for 1 o resultado é 1. Tem a mesma lógica do ||, porém bit
        a bit:

        (89) 0101 1001
        (60) 0011 1100

        E o resultado é:

        (125) 0111 1101
         */
        System.out.println(n1 | n2);

        /*
        O ^ funciona da mesma forma que o |, porém se os dois
        forem 1, o resultado é 0. Tem a mesma lógica do ||, porém bit
        a bit:

        (89) 0101 1001
        (60) 0011 1100

        E o resultado é:

        (101) 0110 0101
         */
        System.out.println(n1 ^ n2);
    }
}