import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadTest {

    @Test
    void name() {
        new Thread(System.out::println).start();

        ExecutorService exec = Executors.newCachedThreadPool();
        //exec.submit(System.out::println); 컴파일 에러
    }
}
