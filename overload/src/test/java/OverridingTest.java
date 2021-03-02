import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

class OverridingTest {

    @Test
    void name() {
        List<Overriding.Wine> wineList = Arrays.asList(
                new Overriding.Wine(), new Overriding.SparklingWine(), new Overriding.Champagne()
        );

        for (Overriding.Wine wine : wineList) {
            System.out.println(wine.name());
        }
    }
}