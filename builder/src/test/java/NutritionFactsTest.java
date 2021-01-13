import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NutritionFactsTest {

    @Test
    void 한_필드를_여러번_초기화() {
        NutritionFacts cocaCola = new NutritionFacts
            .Builder(240, 8)
            .calories(100)
            .calories(200)
            .calories(300)
            .sodium(35)
            .carbohydrate(27)
            .build();

        assertThat(cocaCola.getCalories()).isEqualTo(300);
    }
}