package engineFiles.main.models.Sprites.Entities;

import engineFiles.main.models.CharacterSpriteSheetModel;
import engineFiles.main.models.Sprites.Controlls;
import engineFiles.main.models.Sprites.Sprite;
import org.json.JSONObject;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.*;

//Class containing the general logic of every entity

public abstract class Entity extends Sprite {
    private static final Logger LOG = Logger.getLogger(Entity.class.getName());
    protected int lastMovementIndex = 0;
    protected MovementAnimation animation;
    protected Controlls controlls;
    protected List<Entity> others = new ArrayList<>();
    protected boolean still = false;
    protected int currentMovement;
    protected int health;
    protected int curHealth;
    protected int damage;
    protected int battleDamage = 0;
    protected int speedCounter;
    protected int currentSpeedCount = 0;
    protected int uniqueIndex;
    protected CharacterSpriteSheetModel characterSpriteSheetModel;

    /**
     * @param uniqueIndex
     */
    public void setUniqueIndex(int uniqueIndex) {
        this.uniqueIndex = uniqueIndex;
    }

    /**
     * @return int
     */
    public int getUniqueIndex() {
        return uniqueIndex;
    }

    int movementIndex;

    public Entity(MovementAnimation animation, JSONObject json, int speedCounter) {
        super(json);
        LOG.setUseParentHandlers(false);
        Handler stdout = new StreamHandler(System.out, new SimpleFormatter()) {
    @Override
    public void publish(LogRecord record) {
        super.publish(record);
        flush();
    }
};
        LOG.addHandler(stdout);
        this.animation = animation;
        this.speedCounter = speedCounter;
        // this.coord.setMOD(speed);
        LOG.config("Entity Initialized");
    }

    public Entity(MovementAnimation animation, File f, int speedCounter) {
        super(f);
        LOG.setUseParentHandlers(false);
        Handler stdout = new StreamHandler(System.out, new SimpleFormatter()) {
    @Override
    public void publish(LogRecord record) {
        super.publish(record);
        flush();
    }
};
        LOG.addHandler(stdout);
        this.animation = animation;
        this.speedCounter = speedCounter;
        LOG.config("Entity Initialized");
        // this.coord.setMOD(speed);
    }

    /**
     * @return BufferedImage
     */
    @Override
    public BufferedImage getImg() {
        int movementIndex = getMovementIndex();
        return this.animation.move(movementIndex, still);
    }

    /**
     * @return int
     */
    protected int getMovementIndex() {
        int chosenMovement = currentMovement;
        if (this.controlls.getUp().contains(chosenMovement)) {
            movementIndex = 1;
        } else if (this.controlls.getLeft().contains(chosenMovement)) {
            movementIndex = 2;
        } else if (this.controlls.getRight().contains(chosenMovement)) {
            movementIndex = 3;
        } else if (this.controlls.getDown().contains(chosenMovement)) {
            movementIndex = 0;
        }
        return movementIndex;
    }

    /**
     * @param other
     * @return int
     */
    public abstract int getMovement();

    /**
     * @param other
     */
    public void addOtherEntity(Entity other) {
        this.others.add(other);
    }

    public void movementBlocked() {
        if (!still) {
            switch (getMovementIndex()) {
                case 0:
                    super.getCoord().moveUp();
                    break;
                case 1:
                    super.getCoord().moveDown();
                    break;
                case 2:
                    super.getCoord().moveRight();
                    break;
                case 3:
                    super.getCoord().moveLeft();
                    break;
            }
        }

    }

    /**
     * @return boolean
     */
    public boolean timeToMove() {
        if (this.speedCounter == this.currentSpeedCount) {
            this.currentSpeedCount = 0;
            return true;
        }
        currentSpeedCount++;
        return false;
    }

    /**
     * @return String
     */
    public String getCategoryName() {
        return categoryName;
    }

    /**
     * @return MovementAnimation
     */
    public MovementAnimation getAnimation() {
        return animation;
    }

    /**
     * @return int
     */
    public int getHealth() {
        return health;
    }

    /**
     * @param health
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * @return int
     */
    public int getCurHealth() {
        return curHealth;
    }

    /**
     * @param curHealth
     */
    public void setCurHealth(int curHealth) {
        if (curHealth >= this.health) {
            this.curHealth = health;
        } else {
            this.curHealth = curHealth;
        }
    }

    /**
     * @return int
     */
    public int getDamage() {
        return damage;
    }

    /**
     * @param damage
     */
    public void setDamage(int damage) {
        this.damage = damage;
    }

    /**
     * @return int
     */
    public int getBattleDamage() {
        return battleDamage;
    }

    /**
     * @param battleDamage
     */
    public void setBattleDamage(int battleDamage) {
        this.battleDamage = battleDamage;
    }

    /**
     * @return Controlls
     */
    protected Controlls controllsInit() {
        List<Integer> up = new ArrayList<>();
        List<Integer> down = new ArrayList<>();
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();

        up.add(1);
        down.add(0);
        left.add(2);
        right.add(3);
        return new Controlls(up, down, left, right);
    }

    /**
     * @param characterSpriteSheetModel
     */
    public void setCharacterSpriteSheetModel(CharacterSpriteSheetModel characterSpriteSheetModel) {
        this.characterSpriteSheetModel = characterSpriteSheetModel;
    }

    /**
     * @return CharacterSpriteSheetModel
     */
    public CharacterSpriteSheetModel getCharacterSpriteSheetModel() {
        return characterSpriteSheetModel;
    }

    /**
     * @return int
     */
    public int getSpeedCounter() {
        return speedCounter;
    }
}
