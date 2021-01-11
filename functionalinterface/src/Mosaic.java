import java.util.List;

public class Mosaic {

    private List<Tile> tiles;

    public Mosaic(List<Tile> tiles) {
        this.tiles = tiles;
    }

    public int getSize() {
        return tiles.size();
    }

    public List<Tile> getTiles() {
        return tiles;
    }
}
