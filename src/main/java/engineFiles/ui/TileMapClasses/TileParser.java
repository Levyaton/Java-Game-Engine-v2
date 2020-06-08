package engineFiles.ui.TileMapClasses;

import engineFiles.ui.FolderOP;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.*;

//Class containing tile parsing logic
public class TileParser {
    private static final Logger LOG = Logger.getLogger(TileParser.class.getName());
    /**
     * @param tileSheet
     * @return BufferedImage[]
     */
    public static BufferedImage[] getTiles(File tileSheet) {
        LOG.setUseParentHandlers(false);
        Handler stdout = new StreamHandler(System.out, new SimpleFormatter()) {
    @Override
    public void publish(LogRecord record) {
        super.publish(record);
        flush();
    }
};
        LOG.addHandler(stdout);
        BufferedImage sheet = FolderOP.getImage(tileSheet);
        LOG.config("Tilesheet is " + tileSheet);
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
