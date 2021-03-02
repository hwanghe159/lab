public class Overriding {

    public static class Wine {
        String name() {
            return "포도주";
        }
    }

    public static class SparklingWine extends Wine {
        @Override
        String name() {
            return "발포성 포도주";
        }
    }

    public static class Champagne extends SparklingWine {
        @Override
        String name() {
            return "샴페인";
        }
    }
}
