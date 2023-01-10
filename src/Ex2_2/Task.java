package Ex2_2;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class Task<T> extends FutureTask<T> implements Callable<T>, Comparable<Task<T>>{

    private Callable<T> task;
    private TaskType taskType;

    //Private Constructor
    @NotNull
    private Task(Callable<T> task, TaskType taskType){
        super(task);
        this.task = task;
        this.taskType = taskType;
    }

    //createTask() gets a task and priority and calls the constructor
    @NotNull
    public static Task createTask(Callable task, TaskType taskType){
        return new Task(task, taskType);
    }

    //createTask() gets a task and calls the constructor with default priority
    @NotNull
    public static Task createTask(Callable task){
        return new Task(task, TaskType.OTHER);
    }

    //call() activates the task
    @Override
    public T call() throws Exception {
        try {
           return this.task.call();
        }
        catch (Exception e){
            System.out.println("Error in call() method: " + e);
            return null;
        }
    }

    //getTaskType() returns the priority of the task
    public TaskType getTaskType(){
        return this.taskType;
    }

    //getTask() returns the task
    public Callable<T> getTask(){
        try{
            return this.task;
        }
        catch (Exception e){
            System.out.println("Error in getTask() method: " +e);
            return null;
        }
    }

    //compareTo() compares priorities of 2 tasks.
    @Override
    public int compareTo(Task task) {
        if(this.taskType.getPriorityValue() == task.getTaskType().getPriorityValue()){
            return 0;
        }
        if(this.taskType.getPriorityValue() < task.getTaskType().getPriorityValue()){
            return 1;
        }
        return -1;
    }
}
