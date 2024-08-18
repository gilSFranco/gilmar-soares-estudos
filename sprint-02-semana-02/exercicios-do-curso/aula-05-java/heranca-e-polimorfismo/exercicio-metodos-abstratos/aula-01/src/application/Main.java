package application;

import entities.Circle;
import entities.Rectangle;
import entities.Shape;
import enums.Color;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        int numberShapes;

        List<Shape> list = new ArrayList<Shape>();

        System.out.print("Enter the number of shapes: ");
        numberShapes = sc.nextInt();

        for(int i = 0; i < numberShapes; i++) {
            Color color;
            char shape;
            double width, height, radius;

            System.out.println("Shape #" + (i + 1) + " data: ");
            System.out.print("Rectangle or Circle (r/c): ");
            shape = sc.next().charAt(0);

            System.out.print("Color (BLACK/BLUE/RED): ");
            color = Color.valueOf(sc.next());

            if(shape == 'r') {
                System.out.print("Width: ");
                width = sc.nextDouble();

                System.out.print("Height: ");
                height = sc.nextDouble();

                list.add(new Rectangle(color, width, height));
            } else {
                System.out.print("Radius: ");
                radius = sc.nextDouble();

                list.add(new Circle(color, radius));
            }
        }

        System.out.println();

        System.out.println("SHAPE AREAS: ");

        for(Shape shape : list) {
            System.out.println(String.format("%.2f", shape.area()));
        }

        sc.close();
    }
}