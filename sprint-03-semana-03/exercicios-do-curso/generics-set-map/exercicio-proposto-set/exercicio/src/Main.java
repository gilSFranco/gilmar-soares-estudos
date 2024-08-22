import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Set<Integer> a = new HashSet<>();
        Set<Integer> b = new HashSet<>();
        Set<Integer> c = new HashSet<>();

        int numberStudents;

        System.out.print("How many students for course A? ");
        numberStudents = sc.nextInt();

        for(int i = 0; i < numberStudents; i++) {
            a.add(sc.nextInt());
        }

        System.out.println();

        System.out.print("How many students for course B? ");
        numberStudents = sc.nextInt();

        for(int i = 0; i < numberStudents; i++) {
            b.add(sc.nextInt());
        }

        System.out.println();

        System.out.print("How many students for course C? ");
        numberStudents = sc.nextInt();

        for(int i = 0; i < numberStudents; i++) {
            c.add(sc.nextInt());
        }

        System.out.println();

        Set<Integer> total = new HashSet<>(a);
        total.addAll(b);
        total.addAll(c);

        System.out.println("Total students: " + total.size());
    }
}