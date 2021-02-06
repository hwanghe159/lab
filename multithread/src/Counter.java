public class Counter extends Thread {
    static int share;

    public static void main(String[] args) {
        Counter t1 = new Counter();
        Counter t2 = new Counter();

        t1.start();
        t2.start();
    }

    public void run() {
        for (int count = 0; count < 10; count++) {
            System.out.println(share++);

            try {
                sleep(1000);
            } catch (InterruptedException e) {
            }
        }
    }
}
