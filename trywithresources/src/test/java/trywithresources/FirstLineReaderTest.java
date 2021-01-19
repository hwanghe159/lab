package trywithresources;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tryfinally.MockBufferedReader;

import java.io.BufferedReader;
import java.io.IOException;

class FirstLineReaderTest {

    private FirstLineReader firstLineReader;

    @BeforeEach
    void setUp() {
        BufferedReader mockBufferedReader = new MockBufferedReader();
        this.firstLineReader = new FirstLineReader(mockBufferedReader);
    }

    @Test
    void readLine에_대한_오류가_출력되고_close는_Suppressed_처리된다() throws IOException {
        firstLineReader.read();
    }
}