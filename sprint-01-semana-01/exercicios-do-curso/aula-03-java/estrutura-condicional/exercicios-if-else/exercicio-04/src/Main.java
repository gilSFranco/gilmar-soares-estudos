import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int horaInicio, horaFim, duracao, diferenca, diferencaFim;

        System.out.println("Que horas o jogo teve inicio? ");
        horaInicio = sc.nextInt();

        System.out.println("E que horas acabou? ");
        horaFim = sc.nextInt();

        if(horaInicio > horaFim) {
            diferenca = 24 - horaInicio;
            duracao = diferenca + horaFim;

            System.out.printf("A partida teve a duração de %d horas.", duracao);
        } else if(horaInicio == horaFim) {
            diferenca = 24 - horaInicio;
            duracao = diferenca + horaFim;

            System.out.printf("A partida teve a duração de %d horas.", duracao);
        } else{
            duracao = horaFim - horaInicio;
            System.out.printf("A partida teve a duração de %d horas.", duracao);
        }
    }
}