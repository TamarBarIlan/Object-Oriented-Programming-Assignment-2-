package Ex2_2;//import java.util.concurrent.*;
////import org.junit.platform.commons.logging.Logger;
////import org.junit.platform.commons.logging.LoggerFactory;
//
//public class Main {
//    public static void main(String[] args) {
//
////        public static final Logger logger = LoggerFactory.getLogger(Tests.class);
//
//        CustomExecutor customExecutor = new CustomExecutor();
//        Task task = Task.createTask(()->{
//            int sum = 0;
//            for (int i = 1; i <= 10; i++) {
//                sum += i;
//            }
//            return sum;
//        }, TaskType.COMPUTATIONAL);
//
//        Future<Integer> sumTask = customExecutor.submit(task);
//        final int sum;
//        try {
//            sum = sumTask.get(1, TimeUnit.MILLISECONDS);
//        } catch (InterruptedException | ExecutionException | TimeoutException e) {
//            throw new RuntimeException(e);
//        }
////        logger.info(()-> "Sum of 1 through 10 = " + sum);
//        System.out.println("Sum of 1 through 10 = " + sum);
//        Callable<Double> callable1 = ()-> {
//            return 1000 * Math.pow(1.02, 5);
//        };
//        Callable<String> callable2 = ()-> {
//            StringBuilder sb = new StringBuilder("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
//            return sb.reverse().toString();
//        };
//        // var is used to infer the declared type automatically
//        Future<Double> priceTask = customExecutor.submit(()-> {
//            return 1000 * Math.pow(1.02, 5);
//        }, TaskType.COMPUTATIONAL);
//        Future<String> reverseTask = customExecutor.submit(callable2, TaskType.IO);
//        final Double totalPrice;
//        final String reversed;
//        try {
//            totalPrice = priceTask.get();
//            System.out.println("Current maximum priority = " + customExecutor.getCurrentMax());
//            reversed = reverseTask.get();
//        } catch (InterruptedException | ExecutionException e) {
//            throw new RuntimeException(e);
//        }
//        System.out.println("Reversed String = " + reversed);
//        System.out.println("Total Price = " + totalPrice);
//        System.out.println("Current maximum priority = " + customExecutor.getCurrentMax());
//
//        customExecutor.gracefullyTerminate();
//
//        System.out.println("Hello world!");
//    }
//}