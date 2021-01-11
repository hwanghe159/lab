import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

public class Main {
    static Mosaic create(Supplier<? extends Tile> tileFactory) {
        Tile tile1 = tileFactory.get();
        Tile tile2 = tileFactory.get();
        Tile tile3 = tileFactory.get();
        Tile tile4 = tileFactory.get();
        return new Mosaic(Arrays.asList(tile1, tile2, tile3, tile4));
    }

    public static void main(String[] args) {
        Mosaic mosaic = create(() -> new Tile("꽃무늬"));

        System.out.printf("모자이크의 타일 개수는 %d개 입니다.\n", mosaic.getSize());

        System.out.println("각각의 타입은");
        List<Tile> tiles = mosaic.getTiles();
        for (Tile tile : tiles) {
            System.out.println(tile.getType());
        }
    }
}


