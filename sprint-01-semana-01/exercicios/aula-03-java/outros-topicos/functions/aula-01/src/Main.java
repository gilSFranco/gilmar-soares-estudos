import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        /*
        principais vantagens de se utilizar funções:
        - Modularização, dividir seu programa em várias partes(módulos).
        - Delegação, cada parte do sistema tem sua responsabilidade, delegar
        lógicas á outras partes do programa.
        - Reaproveitamento, reaproveitamento de códigos, evitando duplicidades,
         */

        Scanner sc = new Scanner(System.in);

        int a, b, c;

        System.out.println("Enter three numbers: ");
        a = sc.nextInt();
        b = sc.nextInt();
        c = sc.nextInt();

        int higher = max(a, b, c);

        showResult(higher);

        sc.close();
    }

    public static int max(int a, int b, int c){
        int aux;

        if(a > b && a > c){
            aux = a;
        } else if(b > c){
            aux = b;
        } else{
            aux = c;
        }

        return aux;
    }

    public static void showResult(int higher){
        System.out.printf("Higher: %d!%n", higher);
    }

}