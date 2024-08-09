public class Main {
    public static void main(String[] args) {
        double preco = 34.5;
        /*
        operador ternário, é basicamente um if e else sem as chaves
        se preco for menor que 20, o preco será multiplicado por 0.1
        se não, por 0.05
        */
                                           //verdadeiro
        double desconto = (preco < 20.0) ? preco * 0.1 : preco * 0.05;
                                                         //falso
        System.out.println(desconto);
    }
}