import org.junit.jupiter.api.Test;

class PizzaTest {

    @Test
    void 생성() {
        NyPizza nyPizza = new NyPizza
            .Builder(NyPizza.Size.SMALL)
            .addTopping(Pizza.Topping.SAUSAGE)
            .addTopping(Pizza.Topping.ONION)
            .build();
        Calzone calzone = new Calzone
            .Builder()
            .addTopping(Pizza.Topping.HAM)
            .sauceInside().build();

        System.out.println(nyPizza);
        System.out.println(calzone);
    }

    @Test
    void 같은_토핑_여러번_추가() {
        NyPizza nyPizza = new NyPizza
            .Builder(NyPizza.Size.SMALL)
            .addTopping(Pizza.Topping.SAUSAGE)
            .addTopping(Pizza.Topping.SAUSAGE)
            .addTopping(Pizza.Topping.SAUSAGE)
            .build();

        System.out.println(nyPizza);
    }
}