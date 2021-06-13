package typesafeenumpattern_vs_enum.typesafeenum;

import java.util.function.BiFunction;

public class ExtendedOperation extends Operation {

  public static final ExtendedOperation EXP = new ExtendedOperation("^", (x, y) -> Math.pow(x, y));
  public static final ExtendedOperation REMAINDER = new ExtendedOperation("%", (x, y) -> x % y);

  protected ExtendedOperation(String symbol, BiFunction<Double, Double, Double> func) {
    super(symbol, func);
  }

  @Override
  public double apply(double x, double y) {
    return super.apply(x, y);
  }
}
