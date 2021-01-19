package tryfinally;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class FirstLineReaderTest {

    private FirstLineReader firstLineReader;

    @BeforeEach
    void setUp() {
        MockBufferedReader mockBufferedReader = new MockBufferedReader();
        this.firstLineReader = new FirstLineReader(mockBufferedReader);
    }

    @Test
    void readLine은_무시되고_close에_대한_스택_추적_내역만_남는다() throws IOException {
        firstLineReader.read();
    }
}