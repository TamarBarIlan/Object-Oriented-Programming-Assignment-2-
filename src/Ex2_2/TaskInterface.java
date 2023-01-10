package Ex2_2;

@FunctionalInterface
public interface TaskInterface<T> {

    T createTask() throws Exception;
}
