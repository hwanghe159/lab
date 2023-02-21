import java.util.ArrayList;
import org.junit.jupiter.api.Test;

public class TestCode {

  @Test
  void name() {
    ArrayList<Thread> threads = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      Thread t = new Sample(i);
      t.start();
      threads.add(t);
    }

    for (int i = 0; i < threads.size(); i++) {
      Thread t = threads.get(i);
      try {
        t.join(); // t 쓰레드가 종료할 때까지 기다린다.
      } catch (Exception e) {
      }
    }
    System.out.println("main end.");
  }
}
