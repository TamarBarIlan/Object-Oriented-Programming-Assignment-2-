# Object-Oriented-Programming-Assignment-2-

**Ex2_1:**

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

In the getNumOfLinesThreads() method, an array of threads is created, with one thread for each text file. Each thread is responsible for counting the number of lines in a single text file. These threads are then started and run concurrently. This means that while one thread is counting the number of lines in one text file, other threads can be counting the number of lines in other text files. This allows the program to take advantage of multiple processors and cores, which can greatly increase the speed at which the program counts the number of lines in the text files.
In contrast, the getNumOfLines() method uses a single thread to count the number of lines in all of the text files, one at a time. This means that if there are multiple text files, the program will need to wait for one file to be processed before moving on to the next one, which can greatly slow down the program.

getNumOfLinesThreadPool is faster than getNumOfLinesThreads because it uses the Executor framework, which provides several advantages over using raw threads.
* First, the Executor framework automatically manages a pool of threads, creating new threads as needed and reusing existing threads when possible. This reduces the overhead of creating and destroying threads and improves the performance of the program.

* Second, the Executor framework provides a higher-level abstraction for working with threads, which makes the program more readable and easier to maintain. For example, in the getNumOfLinesThreadPool method, the program submits tasks to the thread pool, and the Executor framework handles the details of creating and running the threads.

* Another benefit is the way it handles the Future objects it returns after the completion of task which makes it more easy to retrieve the results, also the ThreadPool allows you to control the number of thread it uses, that means you can optimize the number of threads to match the number of available cores in your system to avoid thread context switching overhead.

**********************

**Ex2_2:**
 
 **class CustomExecutor<T>:**
 
 This code is a custom implementation of the Java Executor framework. It extends the ThreadPoolExecutor class and adds additional functionality for prioritizing tasks.

The main feature of this custom executor is the use of a priority queue, which ensures that tasks with higher priority are executed first. The priority of tasks is determined by the TaskType enumeration, which assigns a priority value to each type of task.

The CustomExecutor class has several overloaded submit methods. The simplest submit method accepts a single callable and adds it to the priority queue with a default priority value. The other submit methods accept a callable and a TaskType, allowing the developer to specify a priority for the task.

The class also overrides the newTaskFor and afterExecute methods, these methods are used to create and finish task respectively.

The CustomExecutor class maintains an internal array to keep track of the highest priority of a currently queued task, allowing the developer to quickly determine the highest priority task in the queue. This feature is implemented in the getCurrentMax() method, which has O(1) time complexity.

The CustomExecutor class provides a gracefullyTerminate() method, which will stop accepting new tasks and complete all of the tasks it has already received before stopping.

When using this class, it is important to note that the number of threads in the collection of threads in CustomExecutor will be at least half of the number of processors available for the JVM, and at most the number of processors available less 1.

 **class Task<T>:**
 
  This code defines a Task class that extends FutureTask and implements Callable and Comparable<Task<T>>. It is intended to be used as a way to assign priority levels to tasks submitted to a custom thread pool executor (CustomExecutor) that sorts tasks by priority.

The Task class has two constructors:

The first one (Task(Callable<T> task, TaskType taskType)) takes a Callable<T> object (representing the task to be executed) and a TaskType object (representing the priority of the task) as parameters and sets them to the task and taskType fields, respectively.
The second one (Task(Callable<T> task)) takes a Callable<T> object and calls the first constructor with a TaskType object set to TaskType.OTHER (default priority).
The Task class has several methods:

call() which activates the task and returns the result of call() from the callable it holds.
getTaskType() returns the TaskType object representing the priority of the task.
getTask() returns the Callable<T> object representing the task.
compareTo(Task task) compares priorities of two tasks by returning 1, 0, -1 if the current task has greater, equal or less priority than the passed task.
Additionally, it has two createTask static methods that creates a new instance of task class and assigns priority to it.

This class also provides a way to query the highest priority task currently in the queue in constant time, getCurrentMax()


