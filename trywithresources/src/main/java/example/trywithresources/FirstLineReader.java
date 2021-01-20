package example.trywithresources;

import java.io.BufferedReader;
import java.io.IOException;

public class FirstLineReader {
    private final BufferedReader bufferedReader;

    public FirstLineReader(BufferedReader bufferedReader) {
        this.bufferedReader = bufferedReader;
    }

    public String read() throws IOException {
        try (BufferedReader br = this.bufferedReader) {
            return br.readLine();
        }
    }
}
