package engineFiles.GUIs.mainGameGui;

import java.awt.*;

public interface GamePanelInterface {

    //A method that ensures access to currently used Sprites
    Image getRenderGraphics();

    //Method where you check which keys are being pressed, or what should move
    Void updateMovement(Void param);

}
