public class Main {
    public static void main(String[] args) {
        String s = "potato apple lemon";
        String original = "abcde FGHIJ ABC abc DEFG    ";

        String s01 = original.toLowerCase();
        String s02 = original.toUpperCase();
        String s03 = original.trim();
        String s04 = original.substring(2);
        String s05 = original.substring(2,9);
        String s06 = original.replace('a','x');
        String s07 = original.replace("abc","xy");
        int i = original.indexOf("bc");
        int j = original.lastIndexOf("bc");

        String[] vect = s.split(" ");
        String word1 = vect[0];
        String word2 = vect[1];
        String word3 = vect[2];

        System.out.printf("Original: %s-.%n", original);
        System.out.printf("toLowerCase: %s-.%n", s01);
        System.out.printf("trim: %s-.%n", s03);
        System.out.printf("substring(2): %s-.%n", s04);
        System.out.printf("substring(2,9): %s-.%n", s05);
        System.out.printf("replace('a','x'): %s-.%n", s06);
        System.out.printf("replace('abc','xy'): %s-.%n", s07);

        System.out.printf("indexOf('bc'): %s-.%n", i);
        System.out.printf("lastIndexOf('bc'): %s-.%n", j);

        System.out.printf("split: %s, %s e %s-.%n", word1, word2, word3);
    }
}