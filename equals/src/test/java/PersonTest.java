import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PersonTest {

  @DisplayName("equals를 재정의하지 않을땐 == 비교를 한다")
  @Test
  void equalsTest() {
    Person p1 = new Person("김");
    Person p2 = new Person("김");

    assertThat(p1.equals(p2)).isFalse();
  }

  @DisplayName("instanceof 테스트")
  @Test
  void instanceOf() {
    Asian a1 = new Asian("황준호");

    assertThat(a1 instanceof Asian).isTrue();
    assertThat(a1 instanceof Person).isTrue();
    assertThat(a1 instanceof Animal).isTrue();
  }
}