package application;

import java.util.Locale;
import java.util.Scanner;
import entities.Person;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        int number, percentage, count = 0;
        double sum = 0;

        System.out.print("Qual será o numero de pessoas? ");
        number = sc.nextInt();

        Person[] pessoas = new Person[number];

        for(int i = 0; i < pessoas.length; i++) {
            String name;
            int age;
            double height;

            System.out.println();
            sc.nextLine();

            System.out.printf("Qual o nome da pessoa %d°? ", i + 1);
            name = sc.nextLine();

            System.out.printf("Qual a idade da pessoa %d°? ", i + 1);
            age = sc.nextInt();

            System.out.printf("Qual a altura da pessoa %d°? ", i + 1);
            height = sc.nextDouble();

            pessoas[i] = new Person(name, age, height);
        }

        for(int k = 0; k < pessoas.length; k++) {
            sum += pessoas[k].getHeight();
        }

        System.out.printf("\nAltura média das pessoas pesquisadas: %.2f.%n", sum / number);

        for(int k = 0; k < pessoas.length; k++) {
            if(pessoas[k].getAge() < 16) {
                count += 1;
            }
        }

        percentage = count * 100 / number;
        System.out.printf("\nPorcentagem de pessoas com menos de 16 anos, %d%% e seus nomes: %n", percentage);

        for(int k = 0; k < pessoas.length; k++) {
            if(pessoas[k].getAge() < 16) {
                System.out.println(pessoas[k].getName());
            }
        }

        sc.close();
    }
}