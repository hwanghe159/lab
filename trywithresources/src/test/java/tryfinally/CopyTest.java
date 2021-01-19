package tryfinally;

import org.junit.jupiter.api.Test;

import java.io.IOException;

class CopyTest {
    @Test
    void copyTest() throws IOException {
        Copy.copy("src/test/resources/test.txt", "src/test/resources/output.txt");
    }
}