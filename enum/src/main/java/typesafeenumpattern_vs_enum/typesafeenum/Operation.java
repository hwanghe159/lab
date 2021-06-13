package typesafeenumpattern_vs_enum.typesafeenum;

import java.util.function.BiFunction;

public class Operation {

  public static final Operation PLUS = new Operation("+", (x, y) -> x + y);
  public static final Operation MINUS = new Operation("-", (x, y) -> x - y);
  public static final Operation TIMES = new Operation("*", (x, y) -> x * y);
  public static final Operation DIVIDE = new Operation("/", (x, y) -> x / y);

  private final String symbol;
  private final BiFunction<Double, Double, Double> func;

  protected Operation(String symbol, BiFunction<Double, Double, Double> func) {
    this.symbol = symbol;
    this.func = func;
  }

  public double apply(double x, double y) {
    return this.func.apply(x, y);
  }
}
