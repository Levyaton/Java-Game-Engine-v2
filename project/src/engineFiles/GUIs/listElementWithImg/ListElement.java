package engineFiles.GUIs.listElementWithImg;

import engineFiles.ui.Sprite;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ListElement {
    JLayeredPane element;
    Sprite sprite;
    JLabel temp;


    public ListElement(RowListElement re, JFrame overlay){
        this.element = re;
        this.sprite = re.sprite;

        element.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                e.translatePoint(e.getComponent().getLocation().x, e.getComponent().getLocation().y);
                temp.setLocation(e.getX(),e.getY());
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                temp = null;
                overlay.remove(temp);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                temp = new JLabel(new ImageIcon(sprite.getImg()));
                overlay.add(temp);
            }

            @Override
            public void mouseExited(MouseEvent e) {

            }



        });
    }

    public ListElement(SquareListElement se){
        this.element = JLayeredPane.getLayeredPaneAbove(se);
        this.sprite = se.sprite;
    }

    public JLayeredPane getElement() {
        return element;
    }

    public Sprite getSprite() {
        return sprite;
    }
}
