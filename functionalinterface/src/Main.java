import java.util.List;

public class Main {
    public static void main(String[] args) {
        MosaicCreator mosaicCreator = new MosaicCreator();
        Mosaic flowerMosaic = mosaicCreator.create(() -> new Tile("꽃무늬"));
        Mosaic checkMosaic = mosaicCreator.create(() -> new Tile("체크무늬"));

        List<Tile> flowerTiles = flowerMosaic.getTiles();
        for (Tile flowerTile : flowerTiles) {
            System.out.println(flowerTile.getType());
        }
        List<Tile> checkTiles = checkMosaic.getTiles();
        for (Tile checkTile : checkTiles) {
            System.out.println(checkTile.getType());
        }
    }
}


