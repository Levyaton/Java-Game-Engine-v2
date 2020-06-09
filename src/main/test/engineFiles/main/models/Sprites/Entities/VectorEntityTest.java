package engineFiles.main.models.Sprites.Entities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

class VectorEntityTest {

    @Order(1)
    @Test
    void getMovement_followsAVectorBasedPath_returnsTheMovementThatTheVectorDictates() {
        //ACT
        List<Vector> vectors = new ArrayList<>();
        vectors.add(new Vector(1,0));
        vectors.add(new Vector(0,2));
        vectors.add(new Vector(1,1));
        File sourceImage = new File("src/main/java/resources/gameFiles/models/tilesets/basictiles.png");
        MovementAnimation animation = new MovementAnimation(1);
        int speedCounter = 0;
        VectorEntity entity = new VectorEntity(animation, sourceImage, speedCounter, vectors);
        //ASSERT
        Assertions.assertEquals(3, entity.getMovement());
        Assertions.assertEquals(1, entity.getMovement());
        Assertions.assertEquals(3, entity.getMovement());
        Assertions.assertEquals(1, entity.getMovement());
        Assertions.assertEquals(1, entity.getMovement());
        Assertions.assertEquals(3, entity.getMovement());
    }

    @Order(2)
    @Test
    void movementBlocked_actionTakenWhenMovementIsBlocked_returnsTheNumberOfTheOpposingDirectionItWentInLast() {
        List<Vector> vectors = new ArrayList<>();
        vectors.add(new Vector(1,0));
        vectors.add(new Vector(0,2));
        vectors.add(new Vector(1,1));
        File sourceImage = new File("src/main/java/resources/gameFiles/models/tilesets/basictiles.png");
        MovementAnimation animation = new MovementAnimation(1);
        int speedCounter = 0;
        VectorEntity entity = new VectorEntity(animation, sourceImage, speedCounter, vectors);
        //ASSERT
        Assertions.assertEquals(0, entity.getCoord().getX());
        Assertions.assertEquals(0, entity.getCoord().getY());
        entity.getMovement();
        Assertions.assertEquals(1, entity.getCoord().getX());
        Assertions.assertEquals(0, entity.getCoord().getY());
        entity.movementBlocked();
        Assertions.assertEquals(0, entity.getCoord().getX());
        Assertions.assertEquals(0, entity.getCoord().getY());
    }

}