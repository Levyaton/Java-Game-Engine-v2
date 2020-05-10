package engineFiles.GUIs.mainGameGui;

import engineFiles.ui.SpriteCollection;

public interface GamePanelInterface {

    //A method that ensures access to currently used Sprites
    SpriteCollection getSprites();

    //Method where you check which keys are being pressed, or what should move
    Void updateMovement(Void param);

}
