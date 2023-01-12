package Ex2_2;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.*;

public class CustomExecutor<T>  extends ThreadPoolExecutor{

    //arrPriority[] will hold the highest priority of a task currently in the queue
    private int arrPriority[] = new int[10];

    //A task queue which arranges the elements in the queue according to their priority.
    //The number of threads in the collection of threads in CustomExecutor will be at least
    // half of the number of processors available for the JVM, and at most the number of
    // processors available less 1.
     public CustomExecutor(){

        super(Runtime.getRuntime().availableProcessors() / 2, Runtime.getRuntime().availableProcessors() - 1,
                300 , TimeUnit.MILLISECONDS , new PriorityBlockingQueue<>(Runtime.getRuntime().availableProcessors()/2,
                        (t1, t2) -> ((Task)t1).compareTo((Task) t2)));
     }

    //The submit function adds the task to the PriorityBlockingQueue.
    // We will update the array arrPriority[] according to the priority of the new task.
    public Future<T> submit(@NotNull Task task){
        arrPriority[task.getTaskType().getPriorityValue() -1] ++;
//       System.out.println("sum priority = " + arrPriority[task.getTaskType().getPriorityValue() -1] + " type priority "
//               + (task.getTaskType().getPriorityValue() -1) );
//       System.out.println("in submit = " + Arrays.toString(arrPriority));
       return super.submit((Callable<T>) task);
   }

   //submit() function that accepts a callable and a priority
   public Future<T> submit(Callable callable, TaskType taskType){
        Task task = Task.createTask(callable, taskType);
        return submit(task);
    }

    //submit() function that accepts a callable
    public Future<T> submit(Callable callable){
        Task task = Task.createTask(callable);
        return submit(task);
    }

    //The CustomExecutor will stop accepting new tasks, and will complete all the tasks it has already received.
    public void gracefullyTerminate() {
       try {
           super.shutdown();
           super.awaitTermination(2, TimeUnit.SECONDS);
       }
       catch (Exception e){
           System.out.println(e);
       }
    }

    //When CustomExecutor is creating new RunnableFuture it's using CastClass<T>
    // instead of the default FutureTask class
    @Override
    protected <T> RunnableFuture<T> newTaskFor( Callable<T> callable ){
        return new CastClass<T>(callable);
    }

    //After the thread finishes the task, it will exit the queue and update the priority set accordingly.
    @Override
    protected void afterExecute(Runnable r, Throwable t){
        super.afterExecute(r,t);
        CastClass<T> castClass = CastClass.class.cast(r);
        Callable<T> callable = castClass.getCallable();
        Task task = (Task) callable;
//        System.out.println("remove the priority " + (task5.getTaskType().getPriorityValue() - 1) + "and now we have :"
//                + (arrPriority[(task5.getTaskType().getPriorityValue() - 1)]- 1));
        arrPriority[(task.getTaskType().getPriorityValue() - 1)]--;
//        System.out.println("in afterExecute: " + Arrays.toString(arrPriority));
    }

    //getCurrentMax() will find the highest priority of a currently queued task in o(1)
    public int getCurrentMax() {
        for( int i = 0; i < 10; i++){
            if (arrPriority[i] > 0){
                return i+1;
            }
        }
        return 0;
    }
}
