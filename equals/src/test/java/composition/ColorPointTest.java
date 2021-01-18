package composition;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ColorPointTest {

    @Test
    void 추이성을_만족하는_equals_구현() {
        ColorPoint redPoint = new ColorPoint(1, 2, Color.RED);
        Point point = new Point(1, 2);
        ColorPoint bluePoint = new ColorPoint(1, 2, Color.BLUE);

        assertThat(redPoint.equals(point)).isFalse();
        assertThat(point.equals(bluePoint)).isFalse();
        assertThat(redPoint.equals(bluePoint)).isFalse();
    }

}