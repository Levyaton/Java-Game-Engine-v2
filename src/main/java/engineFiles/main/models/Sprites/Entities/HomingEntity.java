package engineFiles.main.models.Sprites.Entities;

import engineFiles.ui.Coordinates;
import org.json.JSONObject;

import java.awt.*;
import java.io.File;

//Class containing the logic of homing entities. They foolow a given entity, if the entity is in a given range
public class HomingEntity extends Entity {
    private Entity target;
    private int range;
    private int targetIndex;

    public HomingEntity(MovementAnimation animation, JSONObject json, Entity target, int range, int speedCounter) {
        super(animation, json, speedCounter);
        super.controlls = super.controllsInit();
        this.categoryName = "homing";
        super.others.add(target);
        this.health = 10;
        this.curHealth = 10;
        this.damage = 1;
        this.target = target;
        this.range = range;
    }

    public HomingEntity(MovementAnimation animation, File f, Entity target, int range, int speedCounter) {
        super(animation, f, speedCounter);
        super.controlls = super.controllsInit();
        super.others.add(target);
        this.categoryName = "homing";
        this.health = 10;
        this.curHealth = 10;
        this.damage = 1;
        this.range = range;
        this.target = target;
    }

    /**
     * @return int
     */
    @Override
    public int getMovement() {
        Rectangle scope = new Rectangle();
        scope.setBounds(this.getCoord().getX() - this.range / 2, this.getCoord().getY() - this.range / 2, range, range);
        // IMPLEMENT MOVEMENT METHOD
        Coordinates target = this.target.getCoord();
        super.still = false;
        int choice;

        int selfY = this.getCoord().getY();
        int selfX = this.getCoord().getX();

        int targetY = target.getY();
        int targetX = target.getX();

        int y = Math.abs(Math.abs(selfY) - Math.abs(targetY));
        int x = Math.abs(Math.abs(selfX) - Math.abs(targetX));
        if (!scope.contains(this.target.getCoord().getBounds())) {
            choice = super.lastMovementIndex;
            super.lastMovementIndex = choice;
            super.still = true;
            super.animation.resetStateIndex();
        } else {

            if (y == x) {
                int random = (int) Math.round(Math.random());
                choice = makeChoice(random, selfY, selfX, targetY, targetX);
            } else {

                int decider;
                if (y > x) {
                    decider = 0;
                } else {
                    decider = 1;
                }
                choice = makeChoice(decider, selfY, selfX, targetY, targetX);
            }
        }
        super.currentMovement = choice;
        return choice;
    }

    /**
     * @param decider
     * @param selfY
     * @param selfX
     * @param targetY
     * @param targetX
     * @return int
     */
    private int makeChoice(int decider, int selfY, int selfX, int targetY, int targetX) {
        int choice = 0;
        switch (decider) {
            case 0:
                if (selfY > targetY) {
                    choice = super.controlls.getUp().get(0);
                    super.coord.moveUp();

                } else {
                    choice = super.controlls.getDown().get(0);
                    super.coord.moveDown();
                }
                break;
            case 1:
                if (selfX > targetX) {
                    choice = super.controlls.getLeft().get(0);
                    super.coord.moveLeft();
                } else {
                    choice = super.controlls.getRight().get(0);
                    super.coord.moveRight();
                }
                break;
        }
        return choice;
    }

    /**
     * @param target
     */
    public void setTarget(Entity target) {
        this.target = target;
    }

    /**
     * @param range
     */
    public void setRange(int range) {
        this.range = range;
    }

    /**
     * @return Entity
     */
    public Entity getTarget() {
        return target;
    }

    /**
     * @return int
     */
    public int getRange() {
        return range;
    }

    /**
     * @return int
     */
    public int getTargetIndex() {
        return targetIndex;
    }

    /**
     * @param targetIndex
     */
    public void setTargetIndex(int targetIndex) {
        this.targetIndex = targetIndex;
    }
}
