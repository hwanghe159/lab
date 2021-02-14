package enumerate;

import java.util.function.BiFunction;

enum PayType {
    WEEKDAY((minsWorked, payRate) -> minsWorked <= PayType.MINS_PER_SHIFT ? 0 : (minsWorked - PayType.MINS_PER_SHIFT) * payRate / 2),
    WEEKEND((minsWorked, payRate) -> minsWorked * payRate / 2);

    private final BiFunction<Integer, Integer, Integer> overtimePay;

    PayType(BiFunction<Integer, Integer, Integer> overtimePay) {
        this.overtimePay = overtimePay;
    }

    private static final int MINS_PER_SHIFT = 8 * 60;

    public int pay(int minsWorked, int payRate) {
        int basePay = minsWorked * payRate;
        return basePay + overtimePay.apply(minsWorked, payRate);
    }
}
