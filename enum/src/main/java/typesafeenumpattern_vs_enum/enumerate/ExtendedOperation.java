package typesafeenumpattern_vs_enum.enumerate;

import java.util.function.BiFunction;

public enum ExtendedOperation implements Operation {
  EXP("^", (x, y) -> Math.pow(x, y)),
  REMAINDER("%", (x, y) -> x % y);

  private final String symbol;
  private final BiFunction<Double, Double, Double> func;

  ExtendedOperation(String symbol, BiFunction<Double, Double, Double> func) {
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