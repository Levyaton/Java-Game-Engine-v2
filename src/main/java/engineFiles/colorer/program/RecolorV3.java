package engineFiles.colorer.program;

import engineFiles.main.models.ColorerModel;
import engineFiles.ui.Settings;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class RecolorV3 {
    int redShiftSeed;
    int greenShiftSeed;
    int blueShiftSeed;

    public RecolorV3(){
        this.redShiftSeed = new Random().nextInt(255);
        this.greenShiftSeed = new Random().nextInt(255);
        this.blueShiftSeed = new Random().nextInt(255);
    }



    public RecolorV3( int redShiftSeed, int greenShiftSeed, int blueShiftSeed){
        this.redShiftSeed = redShiftSeed;
        this.greenShiftSeed = greenShiftSeed;
        this.blueShiftSeed = blueShiftSeed;
    }

    public int getRedShiftSeed() {
        return redShiftSeed;
    }

    public int getGreenShiftSeed() {
        return greenShiftSeed;
    }

    public int getBlueShiftSeed() {
        return blueShiftSeed;
    }

    public BufferedImage recolorLocal(BufferedImage img){
        return recolorAlgorithm(img, this.redShiftSeed, this.blueShiftSeed, this.greenShiftSeed);
    }

    public static BufferedImage recolorAlgorithm(BufferedImage img, int redShiftSeed, int greenShiftSeed, int blueShiftSeed){
        BufferedImage result = copyImage(img);
        for (int x = 0; x < img.getWidth(); x++){
            for(int y = 0; y < img.getHeight(); y++){
                int pixel = img.getRGB(x,y);
                int alpha = (pixel>>24) & 0xff;
                if(alpha > 0){
                    int red = (((pixel>>16) & 0xff) + redShiftSeed) % 255;
                    int green = (((pixel>>8) & 0xff) + greenShiftSeed) % 255;
                    int blue = ((pixel & 0xff) + blueShiftSeed) % 255;
                    Color shiftedColor = new Color(red,green,blue);
                    result.setRGB(x,y,shiftedColor.getRGB());
                }
            }
        }
        return result;
    }

    public static BufferedImage recolor(BufferedImage img){
        return recolor(img,true);
    }

    public static BufferedImage recolor(BufferedImage img, boolean recolor){
        int redShiftSeed = new Random().nextInt(255);
        int greenShiftSeed = new Random().nextInt(255);
        int blueShiftSeed = new Random().nextInt(255);
        Settings.ColorerSettings.redShift = redShiftSeed;
        Settings.ColorerSettings.greenShift = greenShiftSeed;
        Settings.ColorerSettings.blueShift = blueShiftSeed;
        Settings.ColorerSettings.recolor = recolor;
        return recolorAlgorithm(img,redShiftSeed,greenShiftSeed, blueShiftSeed);
    }
    public static BufferedImage recolor(BufferedImage img, int redShiftSeed, int greenShiftSeed, int blueShiftSeed){
        return recolorAlgorithm(img,redShiftSeed,greenShiftSeed, blueShiftSeed);
    }

    private static BufferedImage copyImage(BufferedImage source){
        BufferedImage b = new BufferedImage(source.getWidth(), source.getHeight(), source.getType());
        Graphics g = b.getGraphics();
        g.drawImage(source, 0, 0, null);
        g.dispose();
        return b;
    }

    public static void recolorAndSave(ColorerModel model) throws IOException {
        BufferedImage recolor = recolor(ImageIO.read(new File(model.getTilesetInputDir())), model.getRedShift(), model.getGreenShift(), model.getBlueShift());
        File f = new File(model.getTilesetOutputDir());
        f.createNewFile();
        ImageIO.write(recolor,"png", f);
    }


}
