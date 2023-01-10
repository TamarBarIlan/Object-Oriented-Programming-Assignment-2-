package Ex2_1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ThreadNumLines extends Thread{
    private String fileName;
    private int numOfLine = 0;

    public ThreadNumLines(String filename) {
        this.fileName = filename;
    }

    @Override
    public void run() {
        int sum = 0;
//        System.out.println("in run method");
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
            this.numOfLine = sum;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getNumOfLine() {
        return this.numOfLine;
    }
}
