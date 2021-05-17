package inheritance;

public class ColorPoint extends Point {

  private final Color color;

  public ColorPoint(int x, int y, Color color) {
    super(x, y);
    this.color = color;
  }

//  //대칭성 위반
//  @Override
//  public boolean equals(Object o) {
//    if (!(o instanceof ColorPoint)) {
//      return false;
//    }
//    return super.equals(o) && ((ColorPoint) o).color == color;
//  }

  //추이성 위반
  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Point)) {
      return false;
    }
    if (!(o instanceof ColorPoint)) {
      return o.equals(this);
    }
    return super.equals(o) && ((ColorPoint) o).color == color;
  }
}