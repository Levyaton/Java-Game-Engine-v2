package engineFiles.colorer.program;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class AdvancedColorManager extends ColorManager {

    private ColorObj utilDark1 = null;
    private ColorObj utilDark2 = null;
    private ColorObj utilDark3 = null;
    private ColorObj utilLight1 = null;
    private ColorObj utilLight2 = null;
    private ColorObj utilLight3 = null;
    private ColorObj utilMid1 = null;
    private ColorObj utilMid2 = null;
    private ColorObj utilMid3 = null;

    private ColorObj dark1 = null;
    private ColorObj dark2 = null;
    private ColorObj dark3 = null;
    private ColorObj mid1 = null;
    private ColorObj mid2 = null;
    private ColorObj mid3 = null;
    private ColorObj light1 = null;
    private ColorObj light2 = null;
    private ColorObj light3 = null;
    private Random r = new Random();

    private ArrayList<ColorObj> darkColors;
    private ArrayList<ColorObj> midColors;
    private ArrayList<ColorObj> lightColors;



    AdvancedColorManager(){

        initUtilColors();
        darkColors = getDarkColors();
        midColors = getMidColors();
        lightColors = getLightColors();
        assignColors();
    }


    private void initUtilColors(){
        utilDark1 = getUtilDarkColorObj1();
        utilDark2 = getUtilDarkColorObj2();
        utilDark3 = getUtilDarkColorObj3();
        utilLight1 = getUtilLightColorObj1();
        utilLight2 = getUtilLightColorObj2();
        utilLight3 = getUtilLightColorObj3();
        utilMid1 = getUtilMidColorObj1();
        utilMid2 = getUtilMidColorObj2();
        utilMid3 = getUtilMidColorObj3();
    }

    private void assignColors(){
        assignDarkColors();
        assignLightColors();
        assignMidColors();
    }

    private void assignDarkColors(){
        int index = r.nextInt(darkColors.size());
        dark1 = darkColors.get(index);
        darkColors.remove(index);

        index = r.nextInt(darkColors.size());
        dark2 = darkColors.get(index);
        darkColors.remove(index);

        index = r.nextInt(darkColors.size());
        dark3 = darkColors.get(index);
        darkColors.remove(index);
    }

    private void assignLightColors(){
        int index = r.nextInt(lightColors.size());
        light1 = lightColors.get(index);
        lightColors.remove(index);

        index = r.nextInt(lightColors.size());
        light2 = lightColors.get(index);
        lightColors.remove(index);

        index = r.nextInt(lightColors.size());
        light3 = lightColors.get(index);
        lightColors.remove(index);
    }

    private void assignMidColors(){
        int index = r.nextInt(midColors.size());
        mid1 = midColors.get(index);
        midColors.remove(index);

        index = r.nextInt(midColors.size());
        mid2 = midColors.get(index);
        midColors.remove(index);

        index = r.nextInt(midColors.size());
        mid3 = midColors.get(index);
        midColors.remove(index);
    }




    public ColorObj getUtilDarkColorObj1() {
        String lightShadeHex = "";//DOPLŇ!!!!!
        String midShadeHex = "";//DOPLŇ!!!!!
        String darkShadeHex = "";//DOPLŇ!!!!!
        return new ColorObj(lightShadeHex,midShadeHex,darkShadeHex,"dark1");
    }

    public ColorObj getUtilDarkColorObj2() {
        String lightShadeHex = "";//DOPLŇ!!!!!
        String midShadeHex = "";//DOPLŇ!!!!!
        String darkShadeHex = "";//DOPLŇ!!!!!
        return new ColorObj(lightShadeHex,midShadeHex,darkShadeHex,"dark2");
    }

    public ColorObj getUtilDarkColorObj3() {
        String lightShadeHex = "";//DOPLŇ!!!!!
        String midShadeHex = "";//DOPLŇ!!!!!
        String darkShadeHex = "";//DOPLŇ!!!!!
        return new ColorObj(lightShadeHex,midShadeHex,darkShadeHex,"dark3");
    }

    public ColorObj getUtilMidColorObj1() {
        String lightShadeHex = "";//DOPLŇ!!!!!
        String midShadeHex = "";//DOPLŇ!!!!!
        String darkShadeHex = "";//DOPLŇ!!!!!
        return new ColorObj(lightShadeHex,midShadeHex,darkShadeHex,"mid1");
    }

    public ColorObj getUtilMidColorObj2() {
        String lightShadeHex = "";//DOPLŇ!!!!!
        String midShadeHex = "";//DOPLŇ!!!!!
        String darkShadeHex = "";//DOPLŇ!!!!!
        return new ColorObj(lightShadeHex,midShadeHex,darkShadeHex,"mid2");
    }

    public ColorObj getUtilMidColorObj3() {
        String lightShadeHex = "";//DOPLŇ!!!!!
        String midShadeHex = "";//DOPLŇ!!!!!
        String darkShadeHex = "";//DOPLŇ!!!!!
        return new ColorObj(lightShadeHex,midShadeHex,darkShadeHex,"mid3");
    }

    public ColorObj getUtilLightColorObj1() {
        String lightShadeHex = "";//DOPLŇ!!!!!
        String midShadeHex = "";//DOPLŇ!!!!!
        String darkShadeHex = "";//DOPLŇ!!!!!
        return new ColorObj(lightShadeHex,midShadeHex,darkShadeHex,"light1");
    }

    public ColorObj getUtilLightColorObj2() {
        String lightShadeHex = "";//DOPLŇ!!!!!
        String midShadeHex = "";//DOPLŇ!!!!!
        String darkShadeHex = "";//DOPLŇ!!!!!
        return new ColorObj(lightShadeHex,midShadeHex,darkShadeHex,"light2");
    }

    public ColorObj getUtilLightColorObj3() {
        String lightShadeHex = "";//DOPLŇ!!!!!
        String midShadeHex = "";//DOPLŇ!!!!!
        String darkShadeHex = "";//DOPLŇ!!!!!
        return new ColorObj(lightShadeHex,midShadeHex,darkShadeHex,"light3");
    }

    public void advanceColorSwap(File input, File output) throws IOException {
        BufferedImage image = getImage(input);
        int height = image.getHeight();
        int width = image.getWidth();
        BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        for(int y = 0; y < height; y++){
            for (int x = 0; x < width; x++){
                //Color current = new Color(image.getRGB(x,y));
                if(!isTransparent(image,x,y)){
                    Color corresponding = getAdvanceCorrespondingColor(new Color(image.getRGB(x,y)));
                    newImage.setRGB(x,y,corresponding.getRGB());
                }
                else{
                    System.out.println("Empty");
                }
            }
        }
        ImageIO.write(newImage,"png", output);
    }


    private Color getAdvanceCorrespondingColor(Color input){
        String hexInput = hexColorValue(input);
        if (hexInput.equals(utilDark1.getDarkHex())){
            return Color.decode(dark1.getDarkHex());
        }
        else if (hexInput.equals(utilDark1.getMidHex())){
            return Color.decode(dark1.getMidHex());
        }
        else if (hexInput.equals(utilDark1.getLightHex())){
            return Color.decode(dark1.getLightHex());
        }
        else if (hexInput.equals(utilDark2.getDarkHex())){
            return Color.decode(dark2.getDarkHex());
        }
        else if (hexInput.equals(utilDark2.getMidHex())){
            return Color.decode(dark2.getMidHex());
        }
        else if (hexInput.equals(utilDark2.getLightHex())){
            return Color.decode(dark2.getMidHex());
        }
        else if (hexInput.equals(utilDark3.getDarkHex())){
            return Color.decode(dark3.getDarkHex());
        }
        else if (hexInput.equals(utilDark3.getMidHex())){
            return Color.decode(dark3.getMidHex());
        }
        else if (hexInput.equals(utilDark3.getLightHex())){
            return Color.decode(dark3.getMidHex());
        }
        else if (hexInput.equals(utilMid1.getDarkHex())){
            return Color.decode(mid1.getDarkHex());
        }
        else if (hexInput.equals(utilMid1.getMidHex())){
            return Color.decode(mid1.getMidHex());
        }
        else if (hexInput.equals(utilMid1.getLightHex())){
            return Color.decode(mid1.getLightHex());
        }
        else if (hexInput.equals(utilMid2.getDarkHex())){
            return Color.decode(mid2.getDarkHex());
        }
        else if (hexInput.equals(utilMid2.getMidHex())){
            return Color.decode(mid2.getMidHex());
        }
        else if (hexInput.equals(utilMid2.getLightHex())){
            return Color.decode(mid2.getMidHex());
        }
        else if (hexInput.equals(utilMid3.getDarkHex())){
            return Color.decode(mid3.getDarkHex());
        }
        else if (hexInput.equals(utilMid3.getMidHex())){
            return Color.decode(mid3.getMidHex());
        }
        else if (hexInput.equals(utilMid3.getLightHex())){
            return Color.decode(mid3.getMidHex());
        }
        else if (hexInput.equals(utilLight1.getDarkHex())){
            return Color.decode(light1.getDarkHex());
        }
        else if (hexInput.equals(utilLight1.getMidHex())){
            return Color.decode(light1.getMidHex());
        }
        else if (hexInput.equals(utilLight1.getLightHex())){
            return Color.decode(light1.getLightHex());
        }
        else if (hexInput.equals(utilLight2.getDarkHex())){
            return Color.decode(light2.getDarkHex());
        }
        else if (hexInput.equals(utilLight2.getMidHex())){
            return Color.decode(light2.getMidHex());
        }
        else if (hexInput.equals(utilLight2.getLightHex())){
            return Color.decode(light2.getMidHex());
        }
        else if (hexInput.equals(utilLight3.getDarkHex())){
            return Color.decode(light3.getDarkHex());
        }
        else if (hexInput.equals(utilLight3.getMidHex())){
            return Color.decode(light3.getMidHex());
        }
        else if (hexInput.equals(utilLight3.getLightHex())){
            return Color.decode(light3.getMidHex());
        }
        else{
            return input;
        }


    }

    private String hexColorValue(Color input){
        String hex = Integer.toHexString(input.getRGB());
        hex = "#" + hex.substring(2);
        return hex;
    }











}
