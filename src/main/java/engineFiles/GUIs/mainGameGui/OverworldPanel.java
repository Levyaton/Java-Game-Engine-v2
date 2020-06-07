package engineFiles.GUIs.mainGameGui;

import engineFiles.GUIs.ColorSwitchGui;
import engineFiles.main.game.KeyMap;
import engineFiles.main.models.Area;
import engineFiles.main.models.SaveGame;
import engineFiles.main.models.Sprites.Entities.Entity;
import engineFiles.main.models.Sprites.Entities.OverworldPlayer;
import engineFiles.main.models.Sprites.Items.ItemSprite;
import engineFiles.main.models.Sprites.Sprite;
import engineFiles.main.models.WorldGenModel;
import engineFiles.ui.Coordinates;
import engineFiles.ui.Resolution;
import engineFiles.ui.Settings;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class OverworldPanel extends GamePanel {

    int SPACE_MOD1 = 100;
    int SPACE_MOD2 = 50;

    ColorSwitchGui colorSwitchGui;
    private WorldGenModel worldGenModel;

    private boolean paused = false;

    public OverworldPanel(String panelName, Window window, WorldGenModel worldGenModel) {
        super(worldGenModel.getArea(), worldGenModel.getEntitiesModel().getAllEntities(), panelName, window);
        // Dimension d = new Dimension(800, 700);s
        setLayout(new BorderLayout());
        this.colorSwitchGui = new ColorSwitchGui(worldGenModel.getColorerModel().isRecolor());
        loadSprites(area.getSprites());
        setDoubleBuffered(true);
        this.worldGenModel = worldGenModel;
    }

    private void loadSprites(ArrayList<Sprite> sprites) {
        for (Sprite s : sprites) {
            loadSprite(s);
        }
    }

    private JLabel loadSprite(Sprite s) {
        JLabel image = new JLabel(new ImageIcon(s.getPath()));
        image.setDoubleBuffered(true);
        add(image);
        image.setBounds(s.getCoord().getX(), s.getCoord().getY(), s.getCurrentWidth(), s.getCurrentHeight());
        s.setComponent(image);
        return image;
    }

    private void saveGame() {
        System.out.println("Saving game");
        this.worldGenModel.getColorerModel()
                .setRecolor(Boolean.getBoolean(this.colorSwitchGui.getColorChangeButton().getText()));
        SaveGame.save(this.worldGenModel);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == Settings.controlls.getGameSave()) {
            saveGame();
        }
        if (e.getKeyCode() == KeyEvent.VK_C) {
            colorSwitchGui.setVisible(!colorSwitchGui.isVisible());
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            Coordinates offest = this.getOffset();
            OverworldPlayer player = (OverworldPlayer) getPlayer();

            Rectangle rect1 = new Rectangle(player.getCoord().getX() - offest.getX() - 10,
                    player.getCoord().getY() - offest.getY() - 10, player.getCurrentWidth() + 20,
                    player.getCurrentHeight() + 20);

            for (ItemSprite i : area.getSpritesItems()) {
                Rectangle rect2 = new Rectangle(i.getCoord().getX() - offest.getX() - this.SPACE_MOD2,
                        i.getCoord().getY() - offest.getY() - this.SPACE_MOD2, i.getCurrentWidth(),
                        i.getCurrentHeight());
                if (rect2.intersects(rect1)) {
                    player.getPlayer().addItem(i.getItem());
                    area.removeItem(i);
                }
            }
        }
        KeyMap.setKey(e.getKeyCode(), false);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        KeyMap.setKey(e.getKeyCode(), true);
    }

    @Override
    public Image getRenderGraphics() {
        Coordinates offest = this.getOffset();

        Graphics offgc;
        Image offscreen = null;

        // create the offscreen buffer and associated Graphics
        Dimension dimension = new Dimension(offest.getBounds().width, offest.getBounds().height);
        offscreen = createImage(dimension.width, dimension.height);
        offgc = offscreen.getGraphics();
        // clear the exposed area
        offgc.clearRect(0, 0, dimension.width, dimension.height);
        offgc.setColor(getBackground());
        for (Sprite s : area.getSprites()) {
            if (offest.getBounds().contains(s.getCoord().getBounds())) {
                offgc.drawImage(s.getImg(), s.getCoord().getX() - offest.getX() - this.SPACE_MOD2,
                        s.getCoord().getY() - offest.getY() - this.SPACE_MOD2, s.getCurrentWidth(),
                        s.getCurrentHeight(), this);
            }
        }

        for (ItemSprite i : area.getSpritesItems()) {
            if (offest.getBounds().contains(i.getCoord().getBounds())) {
                offgc.drawImage(i.getImg(), i.getCoord().getX() - offest.getX() - this.SPACE_MOD2,
                        i.getCoord().getY() - offest.getY() - this.SPACE_MOD2, i.getCurrentWidth(),
                        i.getCurrentHeight(), this);
            }
        }

        for (Entity e : entities) {
            if (offest.getBounds().contains(e.getCoord().getBounds())) {
                offgc.drawImage(e.getImg(), e.getCoord().getX() - offest.getX(), e.getCoord().getY() - offest.getY(),
                        e.getCurrentWidth(), e.getCurrentHeight(), this);
            }
        }

        offgc.setColor(getForeground());

        return offscreen;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    private boolean colliding(Sprite checked) {
        Rectangle rect1 = new Rectangle();
        Rectangle rect2 = new Rectangle();

        rect1.setBounds(checked.getCoord().getX() - this.SPACE_MOD2, checked.getCoord().getY() - this.SPACE_MOD2,
                checked.getCurrentWidth(), checked.getCurrentHeight());
        for (Sprite s : area.getSprites()) {
            if (s.isSolid() && !s.equals(checked)) {
                rect2.setBounds(s.getCoord().getX() - this.SPACE_MOD1, s.getCoord().getY() - this.SPACE_MOD1,
                        s.getCurrentWidth(), s.getCurrentHeight());
                if (rect1.intersects(rect2)) {
                    return true;
                }
            }
        }

        for (ItemSprite i : area.getSpritesItems()) {
            rect2.setBounds(i.getCoord().getX() - this.SPACE_MOD1, i.getCoord().getY() - this.SPACE_MOD1,
                    i.getCurrentWidth(), i.getCurrentHeight());
            if (rect1.intersects(rect2)) {
                return true;
            }
        }

        rect1.setBounds(checked.getCoord().getX(), checked.getCoord().getY(), checked.getCurrentWidth(),
                checked.getCurrentHeight());
        for (Entity e : entities) {
            if (!e.equals(checked)) {
                rect2.setBounds(e.getCoord().getX() /* - this.SPACE_MOD1 */, e.getCoord().getY() /*- this.SPACE_MOD1*/,
                        e.getCurrentWidth(), e.getCurrentHeight());
                if (rect1.intersects(rect2)) {
                    e.onCollision(checked);
                    if (e instanceof OverworldPlayer == true) {
                        window.initBattle((OverworldPlayer) e, (Entity) checked);
                    }
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void update() {
        if (!isGamePaused()) {
            for (Entity e : this.entities) {
                if (e.timeToMove()) {
                    e.getMovement();

                    if (colliding(e)) {
                        e.movementBlocked();
                    }
                }

            }
        }
    }

    @Override
    public Coordinates getOffset() {
        Entity player = this.getPlayer();
        int offset_X = 0;
        int offset_y = 0;

        int width = Resolution.SCREEN_WIDTH + this.SPACE_MOD1;
        int height = Resolution.SCREEN_HEIGHT + this.SPACE_MOD1;

        if (player != null) {
            offset_X = player.getCoord().getX() - width / 2;
            offset_y = player.getCoord().getY() - height / 2;
        }

        return new Coordinates(offset_X, offset_y, width, height);
    }

    private Entity getPlayer() {
        for (Entity e : entities) {
            if (e.getCategoryName().equals("player")) {
                return e;
            }
        }

        return null;
    }

    private boolean isGamePaused() {
        return colorSwitchGui.isVisible();
    }

    @Override
    public List<JComponent> getJComponents() {
        List<JComponent> components = new ArrayList<>();
        components.add(colorSwitchGui);
        return components;

    }

    public void removeEntity(Entity removedEntity) {
        entities.remove(removedEntity);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        System.out.println("overworld");
    }
}
