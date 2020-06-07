package engineFiles.ui;

import org.junit.BeforeClass;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest({Settings.class, Coordinates.class})

class CoordinatesTest {

    static double MOD = 10.0;
    int x = 0;
    int y = 0;
    int width = 2;
    int height = 2;
    Coordinates coordinates;


    @BeforeClass
    public static void prepareMocks(){
        PowerMockito.mockStatic(Settings.class);
        PowerMockito.when(Settings.MOVEMENT_SPEED).thenReturn(MOD);
    }


    @Test
    void moveUp_lowersYCoordinatedByMODAmount() {
        //ACT
        coordinates = new Coordinates(x,y,width,height);
        double mod = coordinates.getMOD();
        //ASSERT
        Assertions.assertEquals(y, coordinates.getY());
        coordinates.moveUp();
        Assertions.assertEquals(y-mod, coordinates.getY());
    }

    @Test
    void moveUp_lowersYCoordinatedByModAmountTimesHeight() {
        //ACT
        coordinates = new Coordinates(x,y,width,height);
        double mod = coordinates.getMOD();
        //ASSERT
        Assertions.assertEquals(y, coordinates.getY());
        coordinates.moveUp(mod);
        Assertions.assertEquals(y-(mod*height), coordinates.getY());
    }

    @Test
    void moveDown_IncreasesYCoordinatedByMODAmount() {
        //ACT
        coordinates = new Coordinates(x,y,width,height);
        double mod = coordinates.getMOD();
        //ASSERT
        Assertions.assertEquals(y, coordinates.getY());
        coordinates.moveDown();
        Assertions.assertEquals(y+mod, coordinates.getY());
    }

    @Test
    void testMoveDown_IncreasesYCoordinatedByModAmountTimesHeight() {
        //ACT
        coordinates = new Coordinates(x,y,width,height);
        double mod = coordinates.getMOD();
        //ASSERT
        Assertions.assertEquals(y, coordinates.getY());
        coordinates.moveDown(mod);
        Assertions.assertEquals(y+(mod*height), coordinates.getY());
    }

    @Test
    void moveLeft_lowersXCoordinatedByMODAmount() {
        //ACT
        coordinates = new Coordinates(x,y,width,height);
        double mod = coordinates.getMOD();
        //ASSERT
        Assertions.assertEquals(x, coordinates.getX());
        coordinates.moveLeft();
        Assertions.assertEquals(x-mod, coordinates.getX());
    }

    @Test
    void moveLeft_lowersXCoordinatedByModAmountTimesWidth() {
        //ACT
        coordinates = new Coordinates(x,y,width,height);
        double mod = coordinates.getMOD();
        //ASSERT
        Assertions.assertEquals(x, coordinates.getX());
        coordinates.moveLeft(mod);
        Assertions.assertEquals(x-(mod*width), coordinates.getX());
    }

    @Test
    void moveRight_increasesXCoordinatedByMODAmount() {
        //ACT
        coordinates = new Coordinates(x,y,width,height);
        double mod = coordinates.getMOD();
        //ASSERT
        Assertions.assertEquals(x, coordinates.getX());
        coordinates.moveRight();
        Assertions.assertEquals(x+mod, coordinates.getX());
    }

    @Test
    void testMoveRight_increasesXCoordinatedByModAmountTimesWidth() {
        //ACT
        coordinates = new Coordinates(x,y,width,height);
        double mod = coordinates.getMOD();
        //ASSERT
        Assertions.assertEquals(x, coordinates.getX());
        coordinates.moveRight(mod);
        Assertions.assertEquals(x+(mod*width), coordinates.getX());
    }
}