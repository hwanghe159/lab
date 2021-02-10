package enumerate;

import org.junit.jupiter.api.Test;

class PayrollDayTest {

    @Test
    void test() {
        for (PayrollDay day : PayrollDay.values()){
            System.out.printf("%-10s%d%n", day, day.pay(8 * 60, 1));
        }
    }
}