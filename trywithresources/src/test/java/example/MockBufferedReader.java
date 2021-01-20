package example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

public class MockBufferedReader extends BufferedReader {
    public MockBufferedReader() {
        super(new Reader() {
            @Override
            public int read(char[] cbuf, int off, int len) throws IOException {
                throw new IOException("readLine() 오류!");
            }

            @Override
            public void close() throws IOException {
                throw new IOException("close() 오류!");
            }
        });
    }
}
