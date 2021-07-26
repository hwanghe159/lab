import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.Test;

public class StopThread {

  private static boolean stopRequested = false;

  @Test
  void name() throws InterruptedException {
    System.out.println("시작");
    Thread backgroundThread = new Thread(() -> {
      int i = 0;
      while (!stopRequested) {
        i++;
      }
    });
    backgroundThread.start();
    TimeUnit.SECONDS.sleep(1);
    stopRequested = true;
    System.out.println("끝");
  }
}
