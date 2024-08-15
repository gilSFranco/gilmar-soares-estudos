import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        method1();
        System.out.println("End of program!");
    }

    public static void method1() {
        System.out.println("***METHOD 1 START***");
        method2();
        System.out.println("***METHOD 1 START***");
    }

    public static void method2() {
        System.out.println("***METHOD 2 START***");
        Scanner sc = new Scanner(System.in);

        try{
            int position;
            String[] vect = sc.nextLine().split(" ");
            position = sc.nextInt();
            System.out.println(vect[position]);
        } catch(ArrayIndexOutOfBoundsException e){
            /*
            Trata a exceção de se caso voce tentar acessar uma posição
            no array que não existe
            */
            System.out.println("Invalid position!");
            e.printStackTrace();
            sc.next();
        } catch(InputMismatchException e){
            /*
            Trata a exceção de se caso voce digitar
            um valor errado, tipo digitar um numero,
            quando se espera uma string
            */
            System.out.println("Input error!");
        }

        sc.close();
        System.out.println("***METHOD 2 END***");
    }
}