package engineFiles.main.models.Sprites.Entities;

import org.json.JSONObject;

import java.io.File;
import java.util.List;

//Class containing the logic for entities that follow a pre-determined path, using vectors
public class VectorEntity extends Entity {

    protected List<Vector> vectors;

    protected int vectorIndex = 0;
    protected int vectorCounter = 0;
    protected boolean followingX;

    public VectorEntity(MovementAnimation animation, JSONObject json, int speedCounter, List<Vector> vectors) {
        super(animation, json, speedCounter);
        super.controlls = super.controllsInit();
        this.health = 10;
        this.curHealth = 10;
        this.damage = 1;
        this.categoryName = "vector";
        this.vectors = vectors;
        this.followingX = this.vectors.get(vectorIndex).startWithX;
    }

    public VectorEntity(MovementAnimation animation, File f, int speedCounter, List<Vector> vectors) {
        super(animation, f, speedCounter);
        super.controlls = super.controllsInit();
        this.health = 10;
        this.curHealth = 10;
        this.damage = 1;
        this.categoryName = "vector";
        this.vectors = vectors;
        this.followingX = this.vectors.get(vectorIndex).startWithX;
    }

    /**
     * @return int
     */
    @Override
    public int getMovement() {

        Vector currentVector = this.vectors.get(this.vectorIndex);

        if (this.followingX) {
            if (currentVector.getX() < 0) {
                // left
                super.currentMovement = this.controlls.getLeft().get(0);
                super.coord.moveLeft();
            } else {
                // right
                super.currentMovement = this.controlls.getRight().get(0);
                super.coord.moveRight();
            }
        } else {
            if (currentVector.getY() < 0) {
                // down
                super.currentMovement = this.controlls.getDown().get(0);
                super.coord.moveDown();
            } else {
                // up
                super.currentMovement = this.controlls.getUp().get(0);
                super.coord.moveUp();
            }
        }

        this.vectorCounter++;

        vectorStateCheck(currentVector.startWithX, currentVector.getAbsX(), currentVector.getAbsY());
        return super.currentMovement;
        // IMPLEMENT MOVEMENT METHOD
    }

    /**
     * @param startWithX
     * @param absX
     * @param absY
     */
    private void vectorStateCheck(boolean startWithX, int absX, int absY) {
        if (!changeVectorsCheck(startWithX, absX, absY)) {
            if (this.followingX && this.vectorCounter >= absX || !this.followingX && this.vectorCounter >= absY) {
                this.followingX = !this.followingX;
                this.vectorCounter = 0;
            }
        }
    }

    /**
     * @param startWithX
     * @param absX
     * @param absY
     * @return boolean
     */
    private boolean changeVectorsCheck(boolean startWithX, int absX, int absY) {
        if (this.followingX && !startWithX && this.vectorCounter >= absX
                || !this.followingX && startWithX && this.vectorCounter >= absY) {
            this.vectorIndex = (this.vectorIndex + 1) % this.vectors.size();
            this.vectorCounter = 0;
            this.followingX = this.vectors.get(this.vectorIndex).startWithX;
            return true;
        }
        return false;
    }

    @Override
    public void movementBlocked() {
        Vector current = this.vectors.get(this.vectorIndex);
        if (this.vectorCounter == 0) {
            if (this.followingX == current.startWithX) {
                this.vectorIndex = Math.floorMod(this.vectorCounter - 1, this.vectors.size());
                this.vectorCounter = 0;
                this.followingX = this.vectors.get(this.vectorIndex).startWithX;
            } else {
                this.followingX = !this.followingX;
                if (this.followingX) {
                    if (current.getAbsX() == 0) {
                        this.vectorCounter = 0;
                    } else {
                        this.vectorCounter = Math.floorMod(current.getAbsX() - 1, current.getAbsX());
                    }

                } else {
                    if (current.getAbsY() == 0) {
                        this.vectorCounter = 0;
                    }
                    this.vectorCounter = Math.floorMod(current.getAbsY() - 1, current.getAbsY());
                }
            }

        }

        switch (super.currentMovement) {
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

    /**
     * @return List<Vector>
     */
    public List<Vector> getVectors() {
        return vectors;
    }
}
