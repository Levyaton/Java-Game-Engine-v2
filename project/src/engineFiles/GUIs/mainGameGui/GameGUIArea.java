package engineFiles.GUIs.mainGameGui;

import engineFiles.main.game.KeyMap;
import engineFiles.main.models.OverworldPlayer;
import engineFiles.ui.Settings;
import engineFiles.ui.Sprite;
import engineFiles.ui.SpriteCollection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashSet;

public class GameGUIArea extends JPanel implements KeyListener {





    private SpriteCollection sprites = new SpriteCollection();
    private SpriteCollection staticSprites = new SpriteCollection();
    private SpriteCollection movableSprites = new SpriteCollection();

    public OverworldPlayer getPlayer() {
        return player;
    }

    private OverworldPlayer player;

    private int frameCount = 0;

    //DoubleBuffer
    private Image dbImage;
    private Graphics dbGraphics;


    public GameGUIArea(ArrayList<Sprite> sprites, OverworldPlayer player){
        this.player = player;
        setLayout(null);
        loadSprites(sprites);
        loadPlayer();

        setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
        requestFocus();
        setDoubleBuffered(true);
        this.addKeyListener(this);
    }

    private void loadSprites(ArrayList<Sprite> sprites){
        for (Sprite s: sprites) {
           loadSprite(s);
        }
    }

    private void loadPlayer(){
       // sprites.add(player);
    }

    private JLabel loadSprite(Sprite s){
        JLabel image = new JLabel(new ImageIcon(s.getPath()));
        image.setDoubleBuffered(true);
        add(image);

        image.setBounds(s.getCoord().getX(),s.getCoord().getY(),s.getCurrentWidth(),s.getCurrentHeight());
        s.setComponent(image);
        /*
        if(s.isMovable()){
            movableSprites.add(s);
        }
        else{
            staticSprites.add(s);
        }

         */
        sprites.add(s);
        return image;
    }





    protected void paintComponent(Graphics g, Sprite sprite) {
      // super.paintComponent(g);
        //System.out.println("X: " + sprite.getCoord().getX());
       // System.out.println("Y: " + sprite.getCoord().getY());
       // ((JLabel)sprite.getComponent()).paintImmediately(sprite.getCoord().getX(), sprite.getCoord().getY(), sprite.getCurrentWidth(), sprite.getCurrentHeight());
        g.drawImage(sprite.getImg(), sprite.getCoord().getX(), sprite.getCoord().getY(), this);
        //revalidate();
        //repaint();
    }

    public void update(){
        //removeAll();
        Graphics2D g2d = (Graphics2D) this.getGraphics();
        for (Sprite s: sprites){
            g2d.drawImage(s.getImg(),s.getCoord().getX(), s.getCoord().getY(), this);
        }
        g2d.drawImage(player.getImg(),player.getCoord().getX(), player.getCoord().getY(), this);
        repaint();
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

    public void playerPaint(Graphics g){
        dbImage = createImage(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height);
        dbGraphics = dbImage.getGraphics();
//        System.out.println("X: " + player.getCoord().getX());
//        System.out.println("Y: " + player.getCoord().getY());
        // ((JLabel)sprite.getComponent()).paintImmediately(sprite.getCoord().getX(), sprite.getCoord().getY(), sprite.getCurrentWidth(), sprite.getCurrentHeight());
        g.drawImage(player.getImg(), player.getCoord().getX(), player.getCoord().getY(), this);
        g.drawImage(dbImage,0,0,this);
    }

    @Override
    public void keyTyped(KeyEvent e) {
//        System.out.println(e);
//        if(up.contains(e.getKeyCode())){
//            KeyMap.isUp = true;
//        }
//        if(down.contains(e.getKeyCode())){
//            KeyMap.isDown = true;
//        }
//        if(left.contains(e.getKeyCode())){
//            KeyMap.isLeft = true;
//        }
//        if(right.contains(e.getKeyCode())){
//            KeyMap.isRight = true;
//        }
    }

    public void keyPressed(KeyEvent e) {
        KeyMap.setKey(e.getKeyCode(), false);
    }


    public void keyReleased(KeyEvent e) {
        KeyMap.setKey(e.getKeyCode(), true);
    }

    public void moveUp(){
       // System.out.println("up");
        this.player.getCoord().moveUp();
    }

    public void moveDown(){
        //System.out.println("down");
        this.player.getCoord().moveDown();
    }

    public void moveLeft(){
      //  System.out.println("left");
        this.player.getCoord().moveLeft();
    }

    public void moveRight(){
       // System.out.println("right");
        this.player.getCoord().moveRight();
    }
}
