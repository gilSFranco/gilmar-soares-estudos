import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int a, b;

        System.out.println("Digite o valor de a: ");
        a = sc.nextInt();

        System.out.println("Digite o valor de b: ");
        b = sc.nextInt();

        if(a > b){
            if(a % b == 0){
                System.out.printf("%d e %d são números multiplos.%n", a, b);
            } else{
                System.out.printf("%d e %d não são números multiplos.%n", a, b);
            }
        } else{
            if(b % a == 0){
                System.out.printf("%d e %d são números multiplos.%n", a, b);
            } else{
                System.out.printf("%d e %d não são números multiplos.%n", a, b);
            }
        }
    }
}