package engineFiles.main.models.Sprites.Entities;

import engineFiles.main.models.CharacterSpriteSheetModel;
import engineFiles.main.models.Sprites.Controlls;
import engineFiles.main.models.Sprites.Sprite;
import org.json.JSONObject;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

//Class containing the general logic of every entity

public abstract class Entity extends Sprite {

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

    public void setUniqueIndex(int uniqueIndex) {
        this.uniqueIndex = uniqueIndex;
    }

    public int getUniqueIndex() {
        return uniqueIndex;
    }

    int movementIndex;

    public Entity(MovementAnimation animation, JSONObject json, int speedCounter) {
        super(json);
        this.animation = animation;
        this.speedCounter = speedCounter;
        // this.coord.setMOD(speed);
    }

    public Entity(MovementAnimation animation, File f, int speedCounter) {
        super(f);
        this.animation = animation;
        this.speedCounter = speedCounter;
        // this.coord.setMOD(speed);
    }

    @Override
    public BufferedImage getImg() {
        int movementIndex = getMovementIndex();
        return this.animation.move(movementIndex, still);
    }

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

    public abstract int getMovement();

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

    public boolean timeToMove() {
        if (this.speedCounter == this.currentSpeedCount) {
            this.currentSpeedCount = 0;
            return true;
        }
        currentSpeedCount++;
        return false;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public MovementAnimation getAnimation() {
        return animation;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getCurHealth() {
        return curHealth;
    }

    public void setCurHealth(int curHealth) {
        if (curHealth >= this.health) {
            this.curHealth = health;
        } else {
            this.curHealth = curHealth;
        }
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getBattleDamage() {
        return battleDamage;
    }

    public void setBattleDamage(int battleDamage) {
        this.battleDamage = battleDamage;
    }

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

    public void setCharacterSpriteSheetModel(CharacterSpriteSheetModel characterSpriteSheetModel) {
        this.characterSpriteSheetModel = characterSpriteSheetModel;
    }

    public CharacterSpriteSheetModel getCharacterSpriteSheetModel() {
        return characterSpriteSheetModel;
    }

    public int getSpeedCounter() {
        return speedCounter;
    }
}
