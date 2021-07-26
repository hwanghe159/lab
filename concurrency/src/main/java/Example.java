import java.util.concurrent.atomic.AtomicLong;

public class Example {

  private static final AtomicLong nextSerialNumber = new AtomicLong();

  private static long generateSerialNumber() {
    return nextSerialNumber.getAndIncrement();
  }

  public static void main(String[] args) {
    while (true) {
      try {
        generateSerialNumber();
      } catch (RuntimeException e) {
        System.out.println("끝");
        break;
      }
    }
  }

}
