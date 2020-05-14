package engineFiles.ui.charecterSpriteSheetClasses;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class SpriteSheetParser {


    public static List<List<List<BufferedImage>>> parse(BufferedImage sheet, int rowCountPerCharacter, int columnCountPerCharacter,int spriteWidth,int spriteHeight){
        int spriteCount = (sheet.getHeight() * sheet.getWidth()) /  (spriteHeight * spriteWidth);
        List<List<List<BufferedImage>>> result = new ArrayList<>();
        List<List<BufferedImage>> characters = new ArrayList<>();
        List<BufferedImage> directionalMvement;
        int counter = 0;
        int yStart = 0;
        int xMod = 0;
        int yMod = 0;
        int width =  sheet.getWidth()/(columnCountPerCharacter*spriteWidth);
        while (counter < spriteCount){
            List<List<BufferedImage>> character = new ArrayList<>();
            for (int y = 0; y < rowCountPerCharacter; y++){
                directionalMvement = new ArrayList<>();
                for(int x = 0; x < columnCountPerCharacter; x++) {
                   directionalMvement.add(sheet.getSubimage(x*xMod*spriteWidth, y*spriteHeight + yMod, spriteWidth, spriteHeight));
                }
                character.add(directionalMvement);
            }
            result.add(character);
            counter++;
            xMod++;
            if(xMod == width){
                xMod = 0;
                yMod += spriteHeight*rowCountPerCharacter;
            }
        }
        return result;
    }
}
