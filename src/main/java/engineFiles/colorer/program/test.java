package engineFiles.colorer.program;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class test {
    public static void main(String[] args) {
        String inputDir = "project/src/main.engineFiles/colorer/testFiles/basictiles.png";
        String outputDir = "project/src/main.gameFiles/models/sprites/tilesets/basictiles.png";
        File sourceImage = new File(inputDir);


            try {

               // cm.switchColors(member, new File(outputDir + member.getName()));
                BufferedImage recolor = TileSetColorer.getTilesetRecolor(ImageIO.read(sourceImage), 16,16);
                ImageIO.write(recolor,"png", new File(outputDir));

            } catch (IOException e) {
                System.out.println(inputDir);
                e.printStackTrace();
            }



        //File path = new File("project/src/main.playerGameFiles/sprites/image.png");
        //for(File file:)

    }

    private static BufferedImage getImage(File input) throws IOException {

        BufferedImage tmpImage = ImageIO.read(input);

        BufferedImage image = new BufferedImage(tmpImage.getWidth(), tmpImage.getHeight(), BufferedImage.TYPE_INT_ARGB);
        image.getGraphics().drawImage(tmpImage, 0, 0, null);
        tmpImage.flush();
        return image;
    }

    private void workFiles() {

    }


}
