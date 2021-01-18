package inheritance;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ColorPointTest {

    @Test
    void 추이성을_위배한_equals_구현() {
        ColorPoint redPoint = new ColorPoint(1, 2, Color.RED);
        Point point = new Point(1, 2);
        ColorPoint bluePoint = new ColorPoint(1, 2, Color.BLUE);

        assertThat(redPoint.equals(point)).isTrue();
        assertThat(point.equals(bluePoint)).isTrue();
        assertThat(redPoint.equals(bluePoint)).isFalse();
    }
}