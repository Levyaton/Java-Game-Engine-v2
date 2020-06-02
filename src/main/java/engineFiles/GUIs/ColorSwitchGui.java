package engineFiles.GUIs;

import engineFiles.ui.Settings;

import javax.swing.*;
import java.awt.*;

public class ColorSwitchGui extends JPanel {
    JLabel colorChangeText;
    JButton colorChangeButton;
    JButton exit;
    public ColorSwitchGui(boolean changeColor){
        super();
        Dimension dimension = new Dimension(Settings.screenWidth, Settings.screenHeight);
        this.setPreferredSize(dimension);
        this.colorChangeText = new JLabel();
        this.colorChangeButton = new JButton();
        this.exit = new JButton();
        this.setVisible(false);
        JLabel colorChangeText = new JLabel();
        JButton colorChangeButton = new JButton();
        JButton exit = new JButton();
        exit.setText("close");
        this.setLayout(new BorderLayout());
        colorChangeText.setText("Change color pallet on next load?");
        colorChangeButton.setText(String.valueOf(changeColor));
        colorChangeButton.addActionListener(e -> {
            boolean changeColor1 = Boolean.getBoolean(colorChangeButton.getText());
            colorChangeButton.setText(String.valueOf(!changeColor1));
        });
        exit.addActionListener(e -> {
            this.setVisible(false);
        });

        JPanel top = new JPanel();
        top.setLayout(new FlowLayout());
        top.add(exit);
        this.add(top, BorderLayout.NORTH);
        JPanel middle = new JPanel();
        middle.add(colorChangeText, FlowLayout.LEFT);
        middle.add(colorChangeButton);
        this.add(middle, BorderLayout.CENTER);
    }

    public JLabel getColorChangeText() {
        return colorChangeText;
    }

    public JButton getColorChangeButton() {
        return colorChangeButton;
    }

    public JButton getExit() {
        return exit;
    }
}
