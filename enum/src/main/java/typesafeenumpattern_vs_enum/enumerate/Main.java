package typesafeenumpattern_vs_enum.enumerate;

public class Main {

  public static void main(String[] args) {
    double x = 2;
    double y = 4;
    print(BasicOperation.PLUS, x, y);
    print(ExtendedOperation.REMAINDER, x, y);
  }

  public static void print(Operation op, double x, double y) {
    System.out.printf("%f %s %f = %f%n", x, op, y, op.apply(x, y));
  }
}
