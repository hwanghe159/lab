public class Sample extends Thread {

  int seq;

  public Sample(int seq) {
    this.seq = seq;
  }

  public void run() {
    System.out.println(getName() + " " + this.seq + " thread start.");
    try {
      Thread.sleep(1000);
    } catch (Exception e) {
    }
    System.out.println(getName() + " " + this.seq + " thread end.");
  }
}
