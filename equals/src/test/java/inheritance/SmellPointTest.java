package inheritance;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class SmellPointTest {

  @Test
  void StackOverflowErrorTest() {
    ColorPoint cp = new ColorPoint(0, 0, Color.BLUE);
    SmellPoint sp = new SmellPoint(0, 0);

    assertThatThrownBy(() -> cp.equals(sp))
        .isInstanceOf(StackOverflowError.class);
  }
}