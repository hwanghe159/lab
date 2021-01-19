package withouthashcode;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class PhoneNumberTest {

    @Test
    void hashCode를_구현하지_않고_HashMap_사용_시_오류() {
        Map<PhoneNumber, String> m = new HashMap<>();
        m.put(new PhoneNumber(111, 222, 3333), "제니");

        assertThat(m.get(new PhoneNumber(111, 222, 3333))).isNull();
    }
}