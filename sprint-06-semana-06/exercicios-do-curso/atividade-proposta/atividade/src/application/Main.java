package application;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        LocalDate data = LocalDate.now().plusYears(10);
        LocalDate data2 = LocalDate.now();

        int anoAtual = data.getYear(), anoFuturo = data2.getYear();
        int idade = 0;

        if (anoAtual > anoFuturo) {
            idade = anoAtual - anoFuturo;
        } else{
            idade = anoFuturo - anoAtual;
        }

        System.out.println(anoAtual);
        System.out.println(anoFuturo);
        System.out.println(idade);
    }
}