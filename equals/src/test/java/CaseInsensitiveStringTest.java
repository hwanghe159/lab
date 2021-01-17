import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CaseInsensitiveStringTest {

    @Test
    void 대칭성을_위배한_equals_구현() {
        CaseInsensitiveString ABC = new CaseInsensitiveString("ABC");
        String abc = "abc";

        assertThat(ABC.equals(abc)).isTrue();
        assertThat(abc.equals(ABC)).isFalse();
    }

    @Test
    void 대칭성을_위배한_equals_구현2() {
        CaseInsensitiveString ABC = new CaseInsensitiveString("ABC");
        String abc = "abc";

        List<CaseInsensitiveString> list = new ArrayList<>();
        list.add(ABC);

        assertThat(list.contains(abc)).isFalse();
    }
}