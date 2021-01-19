package withdefaulthashcode;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class PhoneNumberTest {

    @Test
    void 기본_hashCode를_구현하고_HashMap_사용() {
        Map<PhoneNumber, String> m = new HashMap<>();
        m.put(new PhoneNumber(111, 222, 3333), "제니");

        long startTime = System.currentTimeMillis();
        assertThat(m.get(new PhoneNumber(111, 222, 3333))).isEqualTo("제니");
        long estimatedTime = System.currentTimeMillis() - startTime;

        System.out.println(estimatedTime);
    }
}