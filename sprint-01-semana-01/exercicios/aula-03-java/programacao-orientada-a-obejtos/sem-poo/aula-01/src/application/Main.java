package application;

import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        double xA, xB, xC, yA, yB, yC, p, areaX, areaY;

        System.out.println("Enter the measures of triangle X: ");
        xA = sc.nextDouble();
        xB = sc.nextDouble();
        xC = sc.nextDouble();

        System.out.println("\nEnter the measures of triangle Y: ");
        yA = sc.nextDouble();
        yB = sc.nextDouble();
        yC = sc.nextDouble();

        p = (xA + xB + xC) / 2.0;
        areaX = Math.sqrt(p * (p - xA) * (p - xB) * (p - xC));

        p = (yA + yB + yC) / 2.0;
        areaY = Math.sqrt(p * (p - yA) * (p - yB) * (p - yC));

        System.out.printf("\nTriangle X area: %.4f.%n", areaX);
        System.out.printf("Triangle Y area: %.4f.%n", areaY);

        if(areaX > areaY){
            System.out.printf("\nLarger area is X, with %.4f.%n", areaX);
        } else{
            System.out.printf("\nLarger area is Y, with %.4f.%n", areaY);
        }
    }
}