package intenumpattern_vs_enum.enumerate;

import java.util.function.Function;

import static intenumpattern_vs_enum.enumerate.Day.DayType.WEEKDAY;
import static intenumpattern_vs_enum.enumerate.Day.DayType.WEEKEND;

public enum Day {
    MONDAY("월요일", WEEKDAY),
    TUESDAY("화요일", WEEKDAY),
    WEDNESDAY("수요일", WEEKDAY),
    THURSDAY("목요일", WEEKDAY),
    FRIDAY("금요일", WEEKDAY),
    SATURDAY("토요일", WEEKEND),
    SUNDAY("일요일", WEEKEND);

    private final String korean;
    private final DayType dayType;

    Day(String korean, DayType dayType) {
        this.korean = korean;
        this.dayType = dayType;
    }

    public static void printAllHourlyWage(double hourlyWage) {
        System.out.printf("기본 시급이 %.2f원 일때,%n", hourlyWage);
        for (Day day : values()) {
            System.out.printf("%s의 시급은 %.2f원 입니다.%n", day.korean, day.dayType.apply(hourlyWage));
        }
    }

    enum DayType {
        WEEKDAY(x -> x), WEEKEND(x -> 1.5 * x);

        private final Function<Double, Double> additionalPay;

        DayType(Function<Double, Double> additionalPay) {
            this.additionalPay = additionalPay;
        }

        public double apply(double hourlyWage) {
            return additionalPay.apply(hourlyWage);
        }
    }
}
