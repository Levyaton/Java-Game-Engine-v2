package engineFiles.main.models.Sprites.Entities;

import engineFiles.main.game.KeyMap;
import engineFiles.ui.Settings;
import org.json.JSONObject;

import java.io.File;
import java.util.logging.Logger;

//Class containing the logic of controllable entities. The entity is controlled by the user
public class ControllableEntity extends Entity {
    private static final Logger LOG = Logger.getLogger(ControllableEntity.class.getName());
    /**
     * @return int
     */
    public int getTEST_COUNTER() {
        return TEST_COUNTER;
    }

    public int TEST_COUNTER = 0;

    public ControllableEntity(MovementAnimation animation, JSONObject json, int speedCounter) {
        super(animation, json, speedCounter);
        LOG.setUseParentHandlers(true);
        super.controlls = Settings.controlls;
        this.categoryName = "controllable";
        LOG.config("ControllableEntity Initialized");
    }

    public ControllableEntity(MovementAnimation animation, File f, int speedCounter) {
        super(animation, f, speedCounter);
        LOG.setUseParentHandlers(true);
        super.controlls = Settings.controlls;
        this.categoryName = "controllable";
        LOG.config("ControllableEntity Initialized");
    }

    /**
     * @return int
     */
    @Override
    public int getMovement() {
        this.TEST_COUNTER++;
        super.still = false;
        int choice;

        if (KeyMap.isPressed(super.controlls.getRight())) {
            choice = super.controlls.getRight().get(0);
            super.coord.moveRight();
        } else if (KeyMap.isPressed(super.controlls.getLeft())) {
            choice = super.controlls.getLeft().get(0);
            super.coord.moveLeft();

        } else if (KeyMap.isPressed(super.controlls.getDown())) {
            choice = super.controlls.getDown().get(0);
            super.coord.moveDown();

        } else if (KeyMap.isPressed(super.controlls.getUp())) {
            choice = super.controlls.getUp().get(0);
            super.coord.moveUp();
        } else {
            choice = super.lastMovementIndex;
            super.lastMovementIndex = choice;
            super.still = true;
            super.animation.resetStateIndex();
        }
        super.currentMovement = choice;
        return choice;
    }

}
