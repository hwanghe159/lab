package inheritance;

public class SmellPoint extends Point {

  public SmellPoint(int x, int y) {
    super(x, y);
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Point)) {
      return false;
    }
    if (!(o instanceof SmellPoint)) {
      return o.equals(this);
    }
    return super.equals(o);
  }
}
