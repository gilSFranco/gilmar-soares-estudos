package application;

import java.util.Locale;
import java.util.Scanner;
import entities.Student;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        Student student  = new Student();

        System.out.print("Qual seu nome? ");
        student.name = sc.nextLine();

        System.out.println("E quais foram suas notas no 1°, 2° e 3° trimestres " + student.name + "? ");
        student.nota1 = sc.nextDouble();
        student.nota2 = sc.nextDouble();
        student.nota3 = sc.nextDouble();

        System.out.println(student);
    }
}