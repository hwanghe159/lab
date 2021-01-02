public class Main {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        System.out.println(calculator.addFromTo(1, 4));
    }

    static class Calculator {
        public int addFromTo(final int a, final int b) {
            final int sum = 0;
            for (int i = a; i <= b; i++) {
                sum += i;
            }
            return sum;
        }
    }
}

