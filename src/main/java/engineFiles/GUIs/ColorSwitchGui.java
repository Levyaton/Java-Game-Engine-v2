package engineFiles.GUIs;

import engineFiles.ui.Settings;

import javax.swing.*;
import java.awt.*;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.*;

/**
 * Gui meant to help switch the games color setting
 *
 */
public class ColorSwitchGui extends JPanel {
    private static final Logger LOG = Logger.getLogger(ColorSwitchGui.class.getName());
    JLabel colorChangeText;
    JButton colorChangeButton;
    JButton exit;

    /**
     * Recolor GUI
     * 
     * @param changeColor
     * 
     */
    public ColorSwitchGui(boolean changeColor) {
        super();
        LOG.setUseParentHandlers(false);
        Handler stdout = new StreamHandler(System.out, new SimpleFormatter()) {
            @Override
            public void publish(LogRecord record) {
                super.publish(record);
                flush();
            }
        };
        LOG.addHandler(stdout);
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
        LOG.config("ColorSwitchGui Initialized");
    }

    /**
     * @return JLabel
     */
    public JLabel getColorChangeText() {
        return colorChangeText;
    }

    /**
     * @return JButton
     */
    public JButton getColorChangeButton() {
        return colorChangeButton;
    }

    /**
     * @return JButton
     */
    public JButton getExit() {
        return exit;
    }
}
