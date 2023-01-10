package Ex2_1;

import java.io.File;

public class Main {

    public static void deleteFiles(String[] fileNames) {
        File file;
        for (int i = 1; i <= fileNames.length; i++) {
            file = new File(fileNames[i - 1]);
            file.delete();
        }
    }
    public static void main(String[] args) {

        Ex2_1 ex2_1 = new Ex2_1();
        System.out.println("------1-----");
        String[] name = ex2_1.createTextFiles(100, 15, 500000);
        System.out.println();
        // System.out.println(Arrays.toString(name));
        System.out.println("------2-----");
        System.out.println(ex2_1.getNumOfLines(name));
        System.out.println("------3-----");
        System.out.println(ex2_1.getNumOfLinesThreads(name));
        System.out.println("-----4------");
        System.out.println(ex2_1.getNumOfLinesThreadPool(name));

        deleteFiles(name);

    }

}