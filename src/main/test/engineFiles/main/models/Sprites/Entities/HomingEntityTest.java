package engineFiles.main.models.Sprites.Entities;

import org.junit.jupiter.api.Test;

import java.io.File;

class HomingEntityTest {



    @Test
    void getMovement_calculatesMovementDirection_returns0() {
        //ACT
        MovementAnimation animation = new MovementAnimation();
        File f = new File("src/main/java/resources/gameFiles/models/sprites/static/backgrounds/iceBlue.png");
        //Entity target = new VectorEntity();
        int range = 100;
        int speedCounter = 0;
       // HomingEntity entity = new HomingEntity(animation, f,target,range,speedCounter);
        //ASSERT
    }
}