package example.trywithresources;

import example.MockBufferedReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

class FirstLineReaderTest {

    private FirstLineReader firstLineReader;

    @BeforeEach
    void setUp() {
        MockBufferedReader mockBufferedReader = new MockBufferedReader();
        this.firstLineReader = new FirstLineReader(mockBufferedReader);
    }

    @Test
    void readLine에_대한_오류가_출력되고_close는_Suppressed_처리된다() {
        IOException ioException = null;
        Throwable suppressedException = null;
        try {
            firstLineReader.read();
        } catch (IOException e) {
            ioException = e;
            suppressedException = e.getSuppressed()[0];
        }
        assertThat(ioException).hasMessage("readLine() 오류!");
        assertThat(suppressedException).hasMessage("close() 오류!");
    }

    @Test
    void getSuppressed로_Suppressed된_예외를_가져올_수_있다() {
        Throwable suppressedException = null;
        try {
            firstLineReader.read();
        } catch (IOException e) {
            suppressedException = e.getSuppressed()[0];
        }
        assertThat(suppressedException).hasMessage("close() 오류!");
    }
}