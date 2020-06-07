package engineFiles.main.models.Sprites.Entities;

import java.awt.image.BufferedImage;
import java.util.List;

//Class containing the overworld movement animation logic
public class MovementAnimation {

    private List<BufferedImage> down;
    private List<BufferedImage> up;
    private List<BufferedImage> left;
    private List<BufferedImage> right;

    private int movementIndex = 0;

    private int stateIndex = 0;

    private int animationSpeed = 5;

    private int animationCounter = 0;

    private int id;

    public MovementAnimation() {

    }

    public MovementAnimation(int id) {
        this.id = id;
    }

    public MovementAnimation(int animationSpeed, int id) {
        this(id);
        this.animationSpeed = animationSpeed;
    }

    public MovementAnimation(List<BufferedImage> down, List<BufferedImage> up, List<BufferedImage> left,
            List<BufferedImage> right, int id) {
        this(id);
        this.down = down;
        this.up = up;
        this.left = left;
        this.right = right;
    }

    public MovementAnimation(List<BufferedImage> down, List<BufferedImage> up, List<BufferedImage> left,
            List<BufferedImage> right, int animationSpeed, int id) {
        this(down, up, left, right, id);
        this.animationSpeed = animationSpeed;
    }

    /**
     * @return int
     */
    public int getAnimationSpeed() {
        return animationSpeed;
    }

    /**
     * @param animationSpeed
     */
    public void setAnimationSpeed(int animationSpeed) {
        this.animationSpeed = animationSpeed;
    }

    /**
     * @return List<BufferedImage>
     */
    public List<BufferedImage> getDown() {
        return down;
    }

    /**
     * @return List<BufferedImage>
     */
    public List<BufferedImage> getUp() {
        return up;
    }

    /**
     * @return List<BufferedImage>
     */
    public List<BufferedImage> getLeft() {
        return left;
    }

    /**
     * @return List<BufferedImage>
     */
    public List<BufferedImage> getRight() {
        return right;
    }

    /**
     * @param down
     */
    public void setDown(List<BufferedImage> down) {
        this.down = down;
    }

    /**
     * @param up
     */
    public void setUp(List<BufferedImage> up) {
        this.up = up;
    }

    /**
     * @param left
     */
    public void setLeft(List<BufferedImage> left) {
        this.left = left;
    }

    /**
     * @param right
     */
    public void setRight(List<BufferedImage> right) {
        this.right = right;
    }

    /**
     * @return BufferedImage
     */
    public BufferedImage getState() {
        return stateSelect(this.movementIndex).get(this.stateIndex);
    }

    /**
     * @return int
     */
    public int getMovementIndex() {
        return movementIndex;
    }

    public void nextState() {
        if (isAnimationCounterFull()) {
            this.stateIndex = (stateIndex + 1) % stateSelect(movementIndex).size();
        }
    }

    /**
     * @param movementIndex
     * @return List<BufferedImage>
     */
    private List<BufferedImage> stateSelect(int movementIndex) {
        switch (movementIndex) {
            case 0:
                return this.down;
            case 1:
                return this.up;
            case 2:
                return this.left;
            case 3:
                return this.right;
        }
        return null;
    }

    /**
     * @param movementIndex
     * @param standing
     * @return BufferedImage
     */
    public BufferedImage move(int movementIndex, boolean standing) {

        if (movementIndex != this.movementIndex) {
            this.movementIndex = movementIndex;
            resetStateIndex();
        }

        List<BufferedImage> currentMovement = stateSelect(movementIndex);
        BufferedImage current = currentMovement.get(stateIndex);
        if (!standing) {
            nextState();
        }
        return current;
    }

    /**
     * @return boolean
     */
    private boolean isAnimationCounterFull() {
        if (this.animationCounter == this.animationSpeed) {
            this.animationCounter = 0;
            return true;
        }
        animationCounter++;
        return false;
    }

    public void resetStateIndex() {
        this.stateIndex = 1;
        this.animationCounter = 1;
    }

    /**
     * @param row
     * @return boolean
     */
    public boolean addNextRow(List<BufferedImage> row) {
        if (this.down == null) {
            this.down = row;
            return true;
        }
        if (this.left == null) {
            this.left = row;
            return true;
        }
        if (this.right == null) {
            this.right = row;
            return true;
        }
        if (this.up == null) {
            this.up = row;
            return true;
        }
        return false;
    }

    /**
     * @return int
     */
    public int getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

}
