package semestralka.view;

import javax.swing.JFrame;

public class Window extends JFrame {

  public Window(GamePanel gamePanel) {
    setTitle("zelda");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setResizable(false);
    add(gamePanel);
    pack(); // sets size from JPanel
    setLocationRelativeTo(null); // centers window to the screen
    setVisible(true);
  }
}