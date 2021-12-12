import java.util.concurrent.ForkJoinPool;

public class Main {

  public static void main(String[] args) {
    MyRecursiveAction myRecursiveAction = new MyRecursiveAction(24);
    ForkJoinPool forkJoinPool = new ForkJoinPool();
    forkJoinPool.invoke(myRecursiveAction);
  }
}
