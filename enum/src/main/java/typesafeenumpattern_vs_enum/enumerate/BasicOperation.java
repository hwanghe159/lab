package typesafeenumpattern_vs_enum.enumerate;

import java.util.function.BiFunction;

public enum BasicOperation implements Operation {
  PLUS("+", (x, y) -> x + y),
  MINUS("-", (x, y) -> x - y),
  TIMES("*", (x, y) -> x * y),
  DIVIDE("/", (x, y) -> x / y);

  private final String symbol;
  private final BiFunction<Double, Double, Double> func;

  BasicOperation(String symbol, BiFunction<Double, Double, Double> func) {
    this.symbol = symbol;
    this.func = func;
  }

  @Override
  public double apply(double x, double y) {
    return this.func.apply(x, y);
  }

  @Override
  public String toString() {
    return symbol;
  }
}
