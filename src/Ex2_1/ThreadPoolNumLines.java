package Ex2_1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.Callable;

public class ThreadPoolNumLines implements Callable<Integer> {
    private String fileName;

    public ThreadPoolNumLines(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public Integer call() throws Exception {
        int sum = 0;
//        System.out.println("in call method");
        try {
            File file = new File(this.fileName );
            // Create a BufferedReader to read from the file
            BufferedReader reader = new BufferedReader(new FileReader(file));

            // Read each line of the file and add it to the sum
            String line;
            while ((line = reader.readLine()) != null) {
                sum++;
            }
            // Close the reader
            reader.close();
//            System.out.println("sum = " + sum + ", ");

        } catch (IOException e) {
            e.printStackTrace();
        }
//        System.out.println(Thread.currentThread().getName() + " sum = " + sum);
        return sum;
    }
}
