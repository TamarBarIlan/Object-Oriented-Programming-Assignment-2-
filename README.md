# Object-Oriented-Programming-Assignment-2-

This is a Java program that creates text files with a specified number of lines containing the text "Erga and Tamar", then counts the total number of lines in all of the files using three different methods:

* Sequentially reading each file in a loop
* Using a separate thread for each file
* Using a thread pool with a fixed number of threads equal to the number of files

class Ex2_1() contains 4 method:

1. The createTextFiles() method creates n files and writes a random number of lines to each file.

2. The getNumOfLines() method reads each file in a loop and counts the number of lines.

3. The getNumOfLinesThreads() method creates a separate ThreadNumLines thread for each file and counts the number of lines in each file using the getNumOfLine method. 

4. The getNumOfLinesThreadPool() method uses a thread pool with a fixed number of threads equal to the number of files and counts the number of lines in each file using the ThreadNumLines class. The ThreadNumLines class implements the Runnable interface and has a run method that counts the number of lines in a specified file.

The program prints the total time taken by each method to count the lines.

Time analysis:

getNumOfLinesThreadPool() is faster than getNumOfLinesThreads() and getNumOfLines() because it uses a thread pool, which allows it to reuse threads rather than creating new ones for each task. This means that it has less overhead and can execute tasks more efficiently. Additionally, getNumOfLinesThreadPool() uses the ExecutorService interface and its submit() method to submit tasks to the thread pool, which allows it to execute tasks concurrently and wait for their completion without explicitly creating and managing threads. This can also contribute to improved performance.
