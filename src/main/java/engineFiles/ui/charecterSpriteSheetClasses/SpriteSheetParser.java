package engineFiles.ui.charecterSpriteSheetClasses;

import engineFiles.main.models.Sprites.Entities.MovementAnimation;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SpriteSheetParser {


    public static HashMap<Integer, MovementAnimation> parse(BufferedImage sheet, int rowCountPerCharacter, int columnCountPerCharacter, int spriteWidth, int spriteHeight){
        SpriteSheet target = new SpriteSheet(sheet,rowCountPerCharacter,columnCountPerCharacter,spriteWidth,spriteHeight);
        HashMap<Integer, MovementAnimation> animations = new HashMap<>();
        int id = 0;
        int numberOfRows = target.getRowSpriteBlockCount();
        int numberOfColumns = target.getColumnSpriteBlockCount();

        for(int y_mod = 0; y_mod < numberOfRows; y_mod++){
            for(int x_mod = 0; x_mod < numberOfColumns; x_mod++){
                MovementAnimation animation = getAnimationBlock(y_mod,x_mod,target,id);
                animations.put(id, animation);
                id++;
            }
        }

        return animations;

    }

    private static MovementAnimation getAnimationBlock(int y_mod, int x_mod, SpriteSheet sheet, int id){
        MovementAnimation animation = new MovementAnimation(id);
        List<BufferedImage> row = new ArrayList<>();
        for(int y = 0; y < sheet.getRowCountPerCharacter(); y++){
            for(int x = 0; x < sheet.getColumnCountPerCharacter(); x++){
                BufferedImage result = sheet.getSheet().getSubimage(x* sheet.getSpriteWidth() + (x_mod  * sheet.getSpriteWidth()), y  * sheet.getSpriteHeight()+ (y_mod * sheet.getSpriteHeight()), sheet.getSpriteWidth(), sheet.getSpriteHeight());
                row.add(result);
                System.out.println("X = " + x + " Y = " + y);
                //System.out.println(id);
                try {
                    ImageIO.write(result,"png", new File("src/main/java/resources/gameFiles/models/sprites/" + id + y + x +".png"));
                } catch (IOException e) {
                    System.out.println("failed");
                    e.printStackTrace();
                }
            }
            animation.addNextRow(row);
            row = new ArrayList<>();
        }
        return animation;
    }


    private static class SpriteSheet{
        BufferedImage sheet;
        int rowCountPerCharacter;
        int columnCountPerCharacter;
        int spriteHeight;
        int spriteWidth;


        int sheetArea;
        int spriteArea;
        int spriteCount;
        int spriteBlockArea;
        int spriteBlockCount;

        int spriteBlockWidth;
        int spriteBlockHeight;


        int rowSpriteBlockCount;
        int columnSpriteBlockCount;

        public SpriteSheet(BufferedImage sheet, int rowCountPerCharacter, int columnCountPerCharacter,int spriteWidth,int spriteHeight){
            this.sheet = sheet;
            this.rowCountPerCharacter = rowCountPerCharacter;
            this.columnCountPerCharacter = columnCountPerCharacter;
            this.spriteHeight = spriteHeight;
            this.spriteWidth = spriteWidth;
            makeCalculations();
        }

        private void makeCalculations(){
            calculateSheetArea();
            calculateSpriteArea();
            calculateTotalSpriteCount();
            calculateSpriteBlockAre();
            calculateSpriteBlockWidth();
            calculateSpriteBlockHeight();
            calculateRowSpriteBlockCount();
            calculateColumnSpriteBlockCount();
            calculateTotalBlockCount();
        }

        private void calculateSheetArea(){
            this.sheetArea = sheet.getWidth() * sheet.getHeight();
        }

        private void calculateSpriteArea(){
            this.spriteArea = spriteHeight*spriteWidth;
        }

        private void calculateTotalSpriteCount(){
            this.spriteCount = sheetArea / spriteArea;
        }

        private void calculateSpriteBlockAre(){
            this.spriteBlockArea = rowCountPerCharacter * columnCountPerCharacter;
        }

        private void calculateSpriteBlockWidth(){
            this.spriteBlockWidth = columnCountPerCharacter*spriteWidth;
        }

        private void calculateSpriteBlockHeight(){
            this.spriteBlockHeight= rowCountPerCharacter*spriteHeight;
        }

        private void calculateRowSpriteBlockCount(){
            this.rowSpriteBlockCount = sheet.getHeight() / (spriteBlockHeight);
        }

        private void calculateColumnSpriteBlockCount(){
            this.columnSpriteBlockCount = sheet.getWidth() / (spriteBlockWidth);
        }

        private void calculateTotalBlockCount(){
            this.spriteBlockCount = spriteCount / spriteBlockArea;
        }



        public BufferedImage getSheet() {
            return sheet;
        }

        public int getRowCountPerCharacter() {
            return rowCountPerCharacter;
        }

        public int getColumnCountPerCharacter() {
            return columnCountPerCharacter;
        }

        public int getSpriteHeight() {
            return spriteHeight;
        }

        public int getSpriteWidth() {
            return spriteWidth;
        }

        public int getSheetArea() {
            return sheetArea;
        }

        public int getSpriteArea() {
            return spriteArea;
        }

        public int getSpriteCount() {
            return spriteCount;
        }

        public int getSpriteBlockArea() {
            return spriteBlockArea;
        }

        public int getSpriteBlockCount() {
            return spriteBlockCount;
        }

        public int getRowSpriteBlockCount() {
            return rowSpriteBlockCount;
        }

        public int getColumnSpriteBlockCount() {
            return columnSpriteBlockCount;
        }

        public int getSpriteBlockWidth() {
            return spriteBlockWidth;
        }

        public int getSpriteBlockHeight() {
            return spriteBlockHeight;
        }
    }

    public static void main(String[] args) {
        int rowCount = 4;
        int columnCount = 3;
        int spriteWidth = 16;
        int spriteHeight = 16;
        String characterSpriteSheet = "src/main/java/resources/gameFiles/models/characterSpriteSheets/characters.png";
        BufferedImage sheet = null;
        try {
            sheet = ImageIO.read(new File(characterSpriteSheet));
        } catch (IOException e) {
            e.printStackTrace();
        }
        SpriteSheetParser.parse(sheet, rowCount, columnCount,  spriteWidth, spriteHeight);
    }


}
