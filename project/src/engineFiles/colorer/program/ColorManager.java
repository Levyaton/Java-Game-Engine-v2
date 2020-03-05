package engineFiles.colorer.program;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.*;


public class ColorManager {


    private SpecialColorObj specialColorObj;
    private ArrayList<ColorObj> colors = new ArrayList();

    ColorManager(){
        specialColorsInit();
        colorsInit();
    }


    private boolean isTransparent(BufferedImage image, int x, int y) {
        int pixel = image.getRGB(x,y);
        if( (pixel>>24) == 0x00 ) {
            return true;
        }
        return false;
    }

    private BufferedImage getImage(File input) throws IOException {

        BufferedImage tmpImage = ImageIO.read(input);

        BufferedImage image = new BufferedImage(tmpImage.getWidth(), tmpImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
        image.getGraphics().drawImage(tmpImage, 0, 0, null);
        tmpImage.flush();
        return image;
    }

    public void switchColors(File input, File output) throws IOException {
        specialColorObj.setChosen(getRandomColorObj().getLightColor(), getRandomColorObj().getDarkColor(), getRandomColorObj().getMidColor(), getRandomColor());
        BufferedImage image = getImage(input);
        int height = image.getHeight();
        int width = image.getWidth();
        BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        for(int y = 0; y < height; y++){
            for (int x = 0; x < width; x++){
                //Color current = new Color(image.getRGB(x,y));
                if(!isTransparent(image,x,y)){
                    Color corresponding = specialColorObj.getCorrespondingColor(new Color(image.getRGB(x,y)));
                    newImage.setRGB(x,y,corresponding.getRGB());
                }
                else{
                    System.out.println("Empty");
                }
            }
        }
        ImageIO.write(newImage,"png", output);
    }


    private ColorObj getRandomColorObj(){
       return colors.get(new Random().nextInt(colors.size()));
    }

    private Color getRandomColor(){
        return getRandomColorObj().getRandomShade();
    }








    private BufferedImage openImage(File input) throws IOException {
        return ImageIO.read(input);
    }

    private void colorsInit(){
        generateBlack();
        generateBrown();
        generateGreen();
        generatePink();
        generatePurple();
        generateYellow();
        generateWhite();
        genertateBlue();
        generateRed();
    }
    private void specialColorsInit(){
        String random = "#ff0000";
        String midColor = "#b7b7b7";
        String darkColor = "#555555";
        String border = "#000000";
        String lightColor = "#ffffff";
        specialColorObj = new SpecialColorObj(lightColor,darkColor,midColor, border, random,"special");
    }

    private void generateRed(){
        String light = "#ffcccb";
        String dark = "#8B0000";
        String mid = "#FF0000";
        String name = "red";
        generateColor(light,mid, dark,name);
    }

    private void genertateBlue(){
        String light = "#add8e6";
        String dark = "#00008b";
        String mid = "#0000ff";
        String name = "blue";
        generateColor(light,mid, dark,name);
    }

    private void generateGreen(){
        String light = "#90ee90";
        String dark = "#013220";
        String mid = "#008000";
        String name = "green";
        generateColor(light,mid, dark,name);
    }

    private void generateYellow(){
        String light = "#ffffed";
        String mid = "#ffff00";
        String dark = "#9B870C";
        String name = "yellow";
        generateColor(light,mid, dark,name);
    }

    private void generatePurple(){
        String light = "#b19cd9";
        String mid = "#800080";
        String dark = "#301934";
        String name = "purple";
        generateColor(light,mid, dark,name);
    }

    private void generatePink(){
        String light = "#FFB6C1";
        String mid = "#FFC0CB";
        String dark = "#e75480";
        String name = "pink";
        generateColor(light,mid, dark,name);
    }

    private void generateBrown(){
        String light = "#b5651d";
        String mid = "#A52A2A";
        String dark = "#654321";
        String name = "brown";
        generateColor(light,mid, dark,name);
    }

    private void generateWhite(){
        String light = "#ffffff";
        String mid = "#e5e5e5";
        String dark = "#cccccc";
        String name = "white";
        generateColor(light,mid, dark,name);
    }

    private void generateBlack(){
        String light = "#4c4c4c";
        String mid = "#323232";
        String dark = "#191919";
        String name = "black";
        generateColor(light,mid, dark,name);
    }

    private void generateColor(String light, String mid, String dark, String colorName){
        this.colors.add(new ColorObj(light,dark,mid,colorName));
    }


}
