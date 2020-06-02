package engineFiles.colorer.program;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Colorizer_2 {

    private static int a;
    private static int r;
    private static int g;
    private static int b;

    private static Color getRandomColor(Color defRed, Color defBlue, Color defGreen){
        // a = (int)(Math.random()*256); //alpha
        Color[] colors = new Color[]{
                Color.BLUE,Color.CYAN, Color.DARK_GRAY, Color.gray, Color.GREEN, Color.LIGHT_GRAY, Color.MAGENTA, Color.ORANGE, Color.PINK, Color.RED, Color.YELLOW, defRed, defBlue, defGreen
        };
        return colors[new Random().nextInt(colors.length)];
    }

    private static Color getRandomColor(){
       // a = (int)(Math.random()*256); //alpha
       Color[] colors = new Color[]{
         Color.BLUE,Color.CYAN, Color.DARK_GRAY, Color.gray, Color.GREEN, Color.LIGHT_GRAY, Color.MAGENTA, Color.ORANGE, Color.PINK, Color.RED, Color.YELLOW
       };
       return colors[new Random().nextInt(colors.length)];
    }

    public static BufferedImage getCompleteReColor(BufferedImage img){
        Color random = getRandomColor();
        for (int x = 0; x < img.getWidth(); x++) {
            for (int y = 0; y < img.getHeight(); y++) {

                Color pixelColor = new Color(img.getRGB(x, y), true);
                int r = (pixelColor.getRed() + random.getRed()) / 2;
                int g = (pixelColor.getGreen() + random.getGreen()) / 2;
                int b = (pixelColor.getBlue() + random.getBlue()) / 2;
                int a = pixelColor.getAlpha();
                int rgba = (a << 24) | (r << 16) | (g << 8) | b;
                img.setRGB(x, y, rgba);
            }
        }
        return img;
    }

    public static BufferedImage getRGBShiftedReColor(BufferedImage img){

        img.setRGB(0,0, getRandomColor().getRGB());

        return img;
    }

    private static ArrayList<Color> getColors(){
        ArrayList<Color> colors = new ArrayList<>();
        Color[] def = new Color[]{
                Color.BLUE,Color.CYAN, Color.DARK_GRAY, Color.gray, Color.GREEN, Color.LIGHT_GRAY, Color.MAGENTA, Color.ORANGE, Color.PINK, Color.RED, Color.YELLOW
        };
        Collections.addAll(colors, def);
        return colors;
    }
    private static ArrayList<Color> getColors(Color defRed, Color defBlue, Color defGreen){
        ArrayList<Color> colors = new ArrayList<>();
        Color[] def = new Color[]{
                Color.BLUE,Color.CYAN, Color.DARK_GRAY, Color.gray, Color.GREEN, Color.LIGHT_GRAY, Color.MAGENTA, Color.ORANGE, Color.PINK, Color.RED, Color.YELLOW, defRed, defBlue, defGreen
        };
        Collections.addAll(colors, def);
        return colors;
    }

    public static ColorModel getNewColorModel(BufferedImage img){
        ArrayList<Color> colors = getColors();
        int index = new Random().nextInt(colors.size());
        int r = colors.get(index).getRGB();
        colors.remove(index);
        index = new Random().nextInt(colors.size());
        int g = colors.get(index).getRGB();
        colors.remove(index);
        index = new Random().nextInt(colors.size());
        int b = colors.get(index).getRGB();

        return null;
    }





}
