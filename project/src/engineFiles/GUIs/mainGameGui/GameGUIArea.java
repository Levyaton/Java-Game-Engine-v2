package engineFiles.GUIs.mainGameGui;

import engineFiles.main.models.OverworldPlayer;
import engineFiles.ui.Sprite;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GameGUIArea extends JPanel{

    Sprite background;
    ArrayList<Sprite> staticSprites = new ArrayList<>();
    ArrayList<Sprite> movableSprites = new ArrayList<>();
    OverworldPlayer player;
    JLabel playerL;
    int frameCount = 0;

    //DoubleBuffer
    private Image dbImage;
    private Graphics dbGraphics;


    public GameGUIArea(ArrayList<Sprite> sprites, OverworldPlayer player, Sprite background){
        this.player = player;
        this.background = background;
        setLayout(null);
        loadSprites(sprites);
        loadPlayer();

        setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
        requestFocus();
        setDoubleBuffered(true);
    }

    private void loadSprites(ArrayList<Sprite> sprites){
        loadSprite(background);
        for (Sprite s: sprites) {
           loadSprite(s);
        }
    }

    private void loadPlayer(){
        playerL = loadSprite(player);
       // movableSprites.add(player);
    }

    private JLabel loadSprite(Sprite s){
        JLabel image = new JLabel(new ImageIcon(s.getPath()));
        image.setDoubleBuffered(true);
        add(image);

        image.setBounds(s.getCoord().getX(),s.getCoord().getY(),s.getCurrentWidth(),s.getCurrentHeight());
        s.setComponent(image);

        if(s.isMovable()){
            movableSprites.add(s);
        }
        else{
            staticSprites.add(s);
        }
        return image;
    }





    protected void paintComponent(Graphics g, Sprite sprite) {
      // super.paintComponent(g);
      //  System.out.println("X: " + sprite.getCoord().getX());
        //System.out.println("Y: " + sprite.getCoord().getY());
       // ((JLabel)sprite.getComponent()).paintImmediately(sprite.getCoord().getX(), sprite.getCoord().getY(), sprite.getCurrentWidth(), sprite.getCurrentHeight());
        g.drawImage(sprite.getImg(), sprite.getCoord().getX(), sprite.getCoord().getY(), this);
        //revalidate();
        repaint();
    }

    public void doUpdate(){
        //removeAll();

        for(Sprite sprite: movableSprites){
            paint(this.getGraphics(), sprite);
        }




    }

    public int getFrameCount() {
        return frameCount;
    }

    public void setFrameCount(int frameCount) {
        this.frameCount = frameCount;
    }


    public void paint(Graphics g, Sprite sprite){
        dbImage = createImage(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height);
        dbGraphics = dbImage.getGraphics();
        paintComponent(dbGraphics,sprite);
        g.drawImage(dbImage,0,0,this);
    }

    public OverworldPlayer getPlayer(){
        return player;
    }
}
