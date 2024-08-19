import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String strPath;

        System.out.print("Enter a folder path: ");
        strPath = sc.nextLine();

        File path = new File(strPath);

        File[] folders = path.listFiles(File::isDirectory);

        System.out.println("\nFolders found: ");
        for (File folder : folders) {
            System.out.println(folder);
        }

        File[] files = path.listFiles(File::isFile);

        System.out.println("\nFiles found: ");
        for(File file : files) {
            System.out.println(file);
        }

        boolean success = new File(strPath + "\\subdir").mkdirs();

        System.out.println("\nDirectory created successfully: " + success);

        sc.close();
    }
}