public class Main {
    public static void main(String[] args) {
        //Aula sobre precedencia de expressões

        //* / % - São os operadores com a maior precedencia, a ordem é da esquerda para a direita
        //+ - - Estão em segundo lugar

        //Nesse caso, por ordem de precedencia a operação de multiplicação
        //acontece primeiro, depois a divisão
        System.out.println(2 * 6 / 3);

        //Nesse caso, por ordem de precedencia a operação de multiplicação
        //acontece primeiro, depois a soma
        System.out.println(3 + 2 * 4);

        //As parenteses quebram a ordem de precedencia, portanto
        //nesse caso a soma é realizada primeiro
        System.out.println((3 + 2) * 4);

        //As parenteses quebram a ordem de precedencia, portanto
        //nesse caso a soma é realizada primeiro, apos isso, como
        //a divisão esta mais para a esquerda da expressão, ela é
        //realizada primeiro, depois a multiplicação
        System.out.println(60 / (3 + 2) * 4);

        //% - O valor é o resto da divisão, portanto,
        //o valor que aparecerá é 2, pois:
        //14 / 2
        // 2   6
        System.out.println(14 % 3);
    }
}