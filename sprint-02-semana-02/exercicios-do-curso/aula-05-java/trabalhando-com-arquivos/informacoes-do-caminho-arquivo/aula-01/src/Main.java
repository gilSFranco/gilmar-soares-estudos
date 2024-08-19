import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String strPath;

        System.out.print("Enter a file path: ");
        strPath = sc.nextLine();

        File path = new File(strPath);

        System.out.println("\ngetName: " + path.getName());
        System.out.println("getParent: " + path.getParent());
        System.out.println("getPath: " + path.getPath());

        sc.close();
    }
}