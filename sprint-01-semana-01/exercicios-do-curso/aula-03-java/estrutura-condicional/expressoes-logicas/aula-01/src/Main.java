public class Main {
    public static void main(String[] args) {
        int x = 5;

        //para que a expressão utilizando o operador lógico && seja verdadeira, as duas condições devem ser verdadeiras
        if(x <= 20 && x == 10){
            //portanto, essa condição utilizando o operador && não é verdadeira
            System.out.println("O x é menor que o 20 (verdade), porem não é igual ao 10 (falso).");
        }

        if(x > 2 && x < 10){
            //portanto, essa condição é verdadeira
            System.out.println("O x é maior que o 2 (verdade) e é menor que 10 (verdade).");
        }

        //para que a expressão utilizando || seja verdadeira, pelo menos uma condição deve ser verdadeira
        if(x <= 20 || x == 10){
            //portanto, essa condição utilizando o operador || é verdadeira
            System.out.println("O x é menor que o 20 (verdade), porem não é igual ao 10 (falso).");
        }

        if(x < 2 || x > 10){
            //portanto, essa condição é falsa
            System.out.println("O x não é menor que o 2 (falso) e não é maoir que o 10 (falso).");
        }

        //se a expressão for verdadeira o operador lógico !(não) torna a expressão falsa
        if(!(x == 2)){
            System.out.println("O x não é igual ao 2 (falso), porem o ! torna a expressão verdadeira.");
        }

        if(!(x == 5)){
            System.out.println("O x é igual a 5 (verdade), porem o ! torna a expressão falsa.");
        }
    }
}