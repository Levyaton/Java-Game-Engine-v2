package engineFiles.colorer.program;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class test {
    public static void main(String[] args) {
        ColorManager cm = new ColorManager();
        String inputDir = "project/src/engineFiles/colorer/testFiles/";
        String outputDir = "project/src/playerGameFiles/sprites/";
        File sourceImagesDir = new File(inputDir);
        File[] directoryListing = sourceImagesDir.listFiles();

        for (File member : directoryListing) {
            try {

                cm.switchColors(member,new File(outputDir + member.getName()));
            } catch (IOException e) {
                System.out.println(inputDir);
                e.printStackTrace();
            }
        }


        //File path = new File("project/src/playerGameFiles/sprites/image.png");
        //for(File file:)

    }

    private void workFiles(){

    }

    private static BufferedImage getImage(File input) throws IOException {

        BufferedImage tmpImage = ImageIO.read(input);

        BufferedImage image = new BufferedImage(tmpImage.getWidth(), tmpImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
        image.getGraphics().drawImage(tmpImage, 0, 0, null);
        tmpImage.flush();
        return image;
    }



}
