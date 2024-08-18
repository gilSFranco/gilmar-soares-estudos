import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int numero, negativeNumbers = 0;

        System.out.print("Digite um numero: ");
        numero = sc.nextInt();

        int[][] mat = new int[numero][numero];

        System.out.println("\nVoce criou uma matriz de 3 linhas e 3 colunas: ");
        System.out.println();

        for(int l = 0; l < mat.length; l++) {
            for(int c = 0; c < mat[l].length; c++) {
                System.out.printf("Coloque um número na posição (%d,%d): ", (l + 1), (c + 1));
                mat[l][c] = sc.nextInt();
            }
        }

        System.out.println();

        for(int k = 0; k < mat.length; k++) {
            System.out.print(mat[k][k] + " ");
        }

        System.out.println();

        for(int l = 0; l < mat.length; l++) {
            for(int c = 0; c < mat[l].length; c++) {
                if(mat[l][c] < 0) {
                    negativeNumbers++;
                }
            }
        }

        System.out.println("\nNumeros negativos: " + negativeNumbers);

        sc.close();
    }
}