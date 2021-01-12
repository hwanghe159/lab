import java.util.Arrays;
import java.util.function.Supplier;

public class MosaicCreator {

    public Mosaic create(Supplier<? extends Tile> tileFactory) {
        Tile tile1 = tileFactory.get();
        Tile tile2 = tileFactory.get();
        Tile tile3 = tileFactory.get();
        Tile tile4 = tileFactory.get();
        return new Mosaic(Arrays.asList(tile1, tile2, tile3, tile4));
    }
}
