package Ex2_1;

import java.io.*;
import java.io.FileReader;
import java.util.Random;
import java.util.concurrent.*;

public class Ex2_1 {

    public String[] createTextFiles(int n, int seed, int bound){

        String[] filesNames = new String[n];

        Random rand = new Random(seed);
        // Create n files on the desktop
        for (int i = 0; i < n; i++) {

            // get a ramdom number of lines
            int numOfLines = rand.nextInt(bound);
            //System.out.print(numOfLines + ", ");

            try {
                // Create the file
                File file = new File("file_" + i + ".txt");
                file.createNewFile();
                filesNames[i] = "file_" + i + ".txt";

                // Write random lines of "Erga and Tamar" to the file
                BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                for (int j = 0; j < numOfLines; j++) {
                    writer.write("Erga and Tamar");
                    writer.newLine();
                }
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return filesNames;
    }

    public int getNumOfLines(String[] fileNames){

        // Save start time
        long startTime = System.nanoTime();

        // Initialize the sum of lines to 0
        int sum = 0;

        // Iterate over the array of filenames
        for (String filename : fileNames) {
            try {
                File file = new File(filename );
                // Create a BufferedReader to read from the file
                BufferedReader reader = new BufferedReader(new FileReader(file));


                // Read each line of the file and add it to the sum
                String line;
                while ((line = reader.readLine()) != null) {
                    sum++;
                }

                // Close the reader
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //Save end time
        long endTime = System.nanoTime();
        System.out.println("Total time in method 2 = " + (endTime - startTime)/1000 + " micrusecon");
        return sum;
    }

    public int getNumOfLinesThreads(String[] fileNames){

        // Save start time
        long startTime = System.nanoTime();

        int count = 0;
        ThreadNumLines[] thread = new ThreadNumLines[fileNames.length];
        for (int i = 0; i < fileNames.length; i++){
            thread [i]= new ThreadNumLines(fileNames[i]);
            thread[i].start();
        }
        for (int i = 0; i < fileNames.length; i++){
            try {
                thread[i].join();
                count +=thread[i].getNumOfLine();
            }
            catch (Exception e){
                System.out.println(e);
            }
//            System.out.print(thread.getNumOfLine() + ", ");
        }
        //Save end time
        long endTime = System.nanoTime();
        System.out.println("Total time in method 3 = " + (endTime - startTime)/1000 + " micrusecon");
        return count;
    }

    public int getNumOfLinesThreadPool(String[] fileNames) {

        // Save start time
        long startTime = System.nanoTime();

        // create a thread pool with fileNames.length threads
        ExecutorService executor = Executors.newFixedThreadPool(fileNames.length);

        // submit tasks to count the lines in each file
        Future<Integer>[] results = new Future[fileNames.length];

        for (int i = 0; i < fileNames.length; i++) {
            results[i] = executor.submit(new ThreadPoolNumLines(fileNames[i]));
        }
        // wait for all tasks to complete and sum the results
        int totalLines = 0;
        for (int i = 0; i < fileNames.length; i++) {
            try {
                totalLines += results[i].get();
//                System.out.println("totalLine now = " + totalLines);
            }
            catch (Exception e){
                System.out.println(e);
            }
        }
        executor.shutdown();
        //Save end time
        long endTime = System.nanoTime();
        System.out.println("Total time in method 4 = " + (endTime - startTime)/1000 + " micrusecon");
        return  totalLines;
    }

}
