package Ex2_2;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class CastClass<T> extends FutureTask<T> {

    //when CustomExecutor is creating new RunnableFuture it's using CastClass<T> instead
    // of the default FutureTask class, and passing the callable as a parameter.
    //This allows the CustomExecutor updating the count of tasks for each priority level
    // in the arrPriority array.

        private Callable<T> callable;

        public CastClass(Callable<T> callable){
            super(callable);
            this.callable = callable;
        }

        public Callable<T> getCallable(){
            return this.callable;
        }
}
