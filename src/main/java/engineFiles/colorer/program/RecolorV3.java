package engineFiles.colorer.program;

import engineFiles.main.models.ColorerModel;
import engineFiles.ui.Settings;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

//Class used to recolor tilesets or single images
public class RecolorV3 {
    int redShiftSeed;
    int greenShiftSeed;
    int blueShiftSeed;

    public RecolorV3() {
        this.redShiftSeed = new Random().nextInt(255);
        this.greenShiftSeed = new Random().nextInt(255);
        this.blueShiftSeed = new Random().nextInt(255);
    }

    public RecolorV3(int redShiftSeed, int greenShiftSeed, int blueShiftSeed) {
        this.redShiftSeed = redShiftSeed;
        this.greenShiftSeed = greenShiftSeed;
        this.blueShiftSeed = blueShiftSeed;
    }

    /**
     * @return int
     */
    public int getRedShiftSeed() {
        return redShiftSeed;
    }

    /**
     * @return int
     */
    public int getGreenShiftSeed() {
        return greenShiftSeed;
    }

    /**
     * @return int
     */
    public int getBlueShiftSeed() {
        return blueShiftSeed;
    }

    /**
     * @param img
     * @return BufferedImage
     */
    public BufferedImage recolorLocal(BufferedImage img) {
        return recolorAlgorithm(img, this.redShiftSeed, this.blueShiftSeed, this.greenShiftSeed);
    }

    /**
     * @param img
     * @param redShiftSeed
     * @param greenShiftSeed
     * @param blueShiftSeed
     * @return BufferedImage
     */
    public static BufferedImage recolorAlgorithm(BufferedImage img, int redShiftSeed, int greenShiftSeed,
            int blueShiftSeed) {
        BufferedImage result = copyImage(img);
        for (int x = 0; x < img.getWidth(); x++) {
            for (int y = 0; y < img.getHeight(); y++) {
                int pixel = img.getRGB(x, y);
                int alpha = (pixel >> 24) & 0xff;
                if (alpha > 0) {
                    int red = (((pixel >> 16) & 0xff) + redShiftSeed) % 255;
                    int green = (((pixel >> 8) & 0xff) + greenShiftSeed) % 255;
                    int blue = ((pixel & 0xff) + blueShiftSeed) % 255;
                    Color shiftedColor = new Color(red, green, blue);
                    result.setRGB(x, y, shiftedColor.getRGB());
                }
            }
        }
        return result;
    }

    /**
     * @param img
     * @return BufferedImage
     */
    public static BufferedImage recolor(BufferedImage img) {
        return recolor(img, true);
    }

    /**
     * @param img
     * @param recolor
     * @return BufferedImage
     */
    public static BufferedImage recolor(BufferedImage img, boolean recolor) {
        int redShiftSeed = new Random().nextInt(255);
        int greenShiftSeed = new Random().nextInt(255);
        int blueShiftSeed = new Random().nextInt(255);
        Settings.ColorerSettings.redShift = redShiftSeed;
        Settings.ColorerSettings.greenShift = greenShiftSeed;
        Settings.ColorerSettings.blueShift = blueShiftSeed;
        Settings.ColorerSettings.recolor = recolor;
        return recolorAlgorithm(img, redShiftSeed, greenShiftSeed, blueShiftSeed);
    }

    /**
     * @param img
     * @param redShiftSeed
     * @param greenShiftSeed
     * @param blueShiftSeed
     * @return BufferedImage
     */
    public static BufferedImage recolor(BufferedImage img, int redShiftSeed, int greenShiftSeed, int blueShiftSeed) {
        return recolorAlgorithm(img, redShiftSeed, greenShiftSeed, blueShiftSeed);
    }

    /**
     * @param source
     * @return BufferedImage
     */
    private static BufferedImage copyImage(BufferedImage source) {
        BufferedImage b = new BufferedImage(source.getWidth(), source.getHeight(), source.getType());
        Graphics g = b.getGraphics();
        g.drawImage(source, 0, 0, null);
        g.dispose();
        return b;
    }

    /**
     * @param model
     * @throws IOException
     */
    public static void recolorAndSave(ColorerModel model) throws IOException {
        BufferedImage recolor = recolor(ImageIO.read(new File(model.getTilesetInputDir())));
        File f = new File(model.getTilesetOutputDir());
        f.createNewFile();
        System.out.println(f);
        ImageIO.write(recolor, "png", f);
    }
}
