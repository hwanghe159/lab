import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.junit.jupiter.api.Test;

public class SynchronizedTest {

  @Test
  void notThreadSafe() {
    NotThreadSafeClass notThreadSafeClass = new NotThreadSafeClass();
    ExecutorService executorService = Executors.newFixedThreadPool(2);
    for (int i = 0; i < 100; i++) {
      executorService.execute(notThreadSafeClass::addCount);
    }
    System.out.println("result = " + notThreadSafeClass.getCounter());
    executorService.shutdown();
  }

  @Test
  void threadSafe() {
    ThreadSafeClass threadSafeClass = new ThreadSafeClass();
    ExecutorService executorService = Executors.newFixedThreadPool(2);
    for (int i = 0; i < 100; i++) {
      executorService.execute(threadSafeClass::addCount);
    }
    System.out.println("result = " + threadSafeClass.getCounter());
    executorService.shutdown();
  }

  @Test
  void threadSafe2() throws InterruptedException {
    ThreadSafeClass threadSafeClass = new ThreadSafeClass();
    Thread thread1 = new Thread(() -> {
      for (int i = 0; i < 10000; i++) {
        threadSafeClass.addCount();
      }
    });
    Thread thread2 = new Thread(() -> {
      for (int i = 0; i < 10000; i++) {
        threadSafeClass.addCount();
      }
    });
    thread1.start();
    thread2.start();
    thread1.join();
    thread2.join();
    System.out.println("result = " + threadSafeClass.getCounter());
  }


  private static class NotThreadSafeClass {

    private int counter;

    public void addCount() {
      this.counter++;
    }

    public int getCounter() {
      return this.counter;
    }
  }

  private static class ThreadSafeClass {

     private int counter;

//    public ThreadSafeClass() {
//      this.counter = new AtomicInteger(0);
//    }

    public synchronized void addCount() {
      this.counter++;
    }

    public synchronized int getCounter() {
      return this.counter;
    }
  }
}
