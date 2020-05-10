package engineFiles.colorer.program;

import java.awt.*;
import java.awt.image.BufferedImage;

public class TileSetColorer {

    public static  BufferedImage getTilesetRecolor(BufferedImage tileset, int subWidth, int subHeight){
        Graphics2D g2d = tileset.createGraphics();
        int width = tileset.getWidth();
        int height = tileset.getHeight();

        int x = 0;
        int y = 0;

        while (x < width){
            while (y < height){
                BufferedImage tile = tileset.getSubimage(x , y , subWidth, subHeight);
                tile = Colorizer_2.getCompleteReColor(tile);
                g2d.drawImage(tile, x, y, null);
                y += 16;
            }
            y = 0;
            x +=16;
        }
        g2d.dispose();
        return tileset;
    }

}
