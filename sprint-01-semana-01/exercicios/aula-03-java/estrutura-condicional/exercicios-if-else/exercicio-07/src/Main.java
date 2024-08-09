import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        double x, y;

        System.out.println("Digite o valor de X: ");
        x = sc.nextDouble();

        System.out.println("Digite o valor de Y: ");
        y = sc.nextDouble();

        if(x > 0 && y > 0){
            System.out.printf("(%.1f,%.1f) = Q1.", x, y);
        } else if(x == 0 && y == 0){
            System.out.printf("(%.1f,%.1f) = Ponto de Origem.", x, y);
        } else if(x == 0){
            System.out.println("Eixo X");
        } else if(y == 0){
            System.out.println("Eixo Y");
        } else if(x < 0 && y < 0){
            System.out.printf("(%.1f,%.1f) = Q3.", x, y);
        } else{
            if(x < 0 && y > 0){
                System.out.printf("(%.1f,%.1f) = Q2.", x, y);
            } else{
                System.out.printf("(%.1f,%.1f) = Q4.", x, y);
            }
        }
    }
}