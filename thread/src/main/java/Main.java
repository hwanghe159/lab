import java.util.ArrayList;

public class Main {

  private static boolean ready;
  private static int number;

  public static void main(String[] args) {
    new String("aaa")
    new ReaderThread().start();
    number = 42;
    ready = true;
    System.out.println(Thread.currentThread().getName() + ": " + number);
  }

  private static class ReaderThread extends Thread {

    @Override
    public void run() {
      while (!ready) {
        System.out.println(Thread.currentThread().getName() + ": " + number);
        Thread.yield();
      }
      System.out.println(Thread.currentThread().getName() + ": " + number);
    }
  }
}
