import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int linhas, colunas, numero;

        System.out.print("Qual a quantidade de linhas? ");
        linhas = sc.nextInt();

        System.out.print("E a quantidade de colunas? ");
        colunas = sc.nextInt();

        int[][] matriz = new int[linhas][colunas];

        System.out.println();

        for(int l = 0; l < matriz.length; l++) {
            for(int c = 0; c < matriz[l].length; c++) {
                System.out.printf("Digite o numero da posição (%d, %d): ", (l + 1), (c + 1));
                matriz[l][c] = sc.nextInt();
            }
        }

        System.out.print("\nQual o numero que voce quer achar? ");
        numero = sc.nextInt();

        for(int l = 0; l < matriz.length; l++) {
            for(int c = 0; c < matriz[l].length; c++) {
                if(matriz[l][c] == numero) {
                    System.out.printf("Posição (%d, %d):%n", l, c);

                    if (c > 0) {
                        System.out.println("Left: " + matriz[l][c-1]);
                    }
                    if (l > 0) {
                        System.out.println("Up: " + matriz[l-1][c]);
                    }
                    if (c < matriz[l].length-1) {
                        System.out.println("Right: " + matriz[l][c+1]);
                    }
                    if (l < matriz.length-1) {
                        System.out.println("Down: " + matriz[l+1][c]);
                    }
                }
            }
        }

        sc.close();
    }
}