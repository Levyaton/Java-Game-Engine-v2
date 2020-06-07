package engineFiles.ui.TileMapClasses;

import engineFiles.ui.FolderOP;

import java.awt.image.BufferedImage;
import java.io.File;

//Class containing tile parsing logic
public class TileParser {

    /**
     * @param tileSheet
     * @return BufferedImage[]
     */
    public static BufferedImage[] getTiles(File tileSheet) {
        BufferedImage sheet = FolderOP.getImage(tileSheet);
        System.out.println(tileSheet);
        int width = sheet.getWidth() / 16;
        int height = sheet.getHeight() / 16;

        BufferedImage[] tiles = new BufferedImage[120];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                tiles[x + (y * width)] = sheet.getSubimage(x * 16, y * 16, 16, 16);
            }
        }
        return tiles;
    }

}
