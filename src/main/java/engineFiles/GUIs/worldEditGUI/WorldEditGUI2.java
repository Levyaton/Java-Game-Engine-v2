package engineFiles.GUIs.worldEditGUI;

import engineFiles.GUIs.listElementWithImg.ListElement;
import engineFiles.GUIs.listElementWithImg.RowListElement;
import engineFiles.main.models.Sprites.Sprite;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;

public class WorldEditGUI2 extends JFrame {
    private String spritePathDir = "project/src/main.gameFiles/models/sprites/static/";
    private JPanel body;
    private JPanel toolBar;
    private JPanel preview;
    private JScrollPane scroll;
    private JPanel scrollBody;
    private JPanel listElelement;
    private JPanel leftWing;
    private JFrame gui;
    private ArrayList<ListElement> listItems = new ArrayList<>();
    private ArrayList<Sprite> sprites = new ArrayList<>();


    public WorldEditGUI2() {
        super("WorldEditGUI2");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //ADD ELEMENTS HERE
        generateBody();
        //END
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void generateToolbar() {
        this.toolBar = new JPanel();
        //toolBar.setPreferredSize(new Dimension(200,10));
        toolBar.add(new Button("bckgroundImg"));
        toolBar.add(new JTextField("Area Name"));
        toolBar.add(new JButton("Save"));
    }

    private void generatePreview() {
        this.preview = new JPanel();
        preview.add(new JLabel(new ImageIcon(this.spritePathDir + "backgrounds/iceBlue.png")));
    }

    private void generateLeftWing() {
        generatePreview();
        generateToolbar();

        leftWing = new JPanel();
        leftWing.setLayout(new BoxLayout(leftWing, BoxLayout.Y_AXIS));
        //leftWing.setLayout(new FlowLayout());

        // leftWing.setLayout(new GridLayout(2, 1, 1, 1));
        leftWing.add(toolBar);
        leftWing.add(preview);
    }

    private void generateScroll() {
        this.scroll = new JScrollPane();
        this.scrollBody = new JPanel();
        scrollBody.setLayout(new GridBagLayout());
        scroll.setLayout(new ScrollPaneLayout());
        fillScrollBody();
        scroll.add(scrollBody);
    }

    private GridBagConstraints getConstraints() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = listItems.size();
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.insets = new Insets(4, 4, 4, 10);
        return gbc;
    }

    private void generateBody() {
        body = new JPanel();
        generateLeftWing();
        generateScroll();

        // body.setLayout(new GridLayout(1, 2, 10, 15));
        //body.setLayout(new F);
        body.setLayout(new GridLayout(1, 2));
        body.add(leftWing);
        //body.add(scroll);
        scroll = new JScrollPane(scrollBody);
        body.add(scroll);
        add(body);
    }

    private void fillScrollBody() {
        testLineLayout();
    }

    private void spriteDisplay() {
        //  for()
    }

    private void testSquareLayout() {
        for (int x = 0; x < 20; x++) {
            //SquareListElement le = new SquareListElement(FolderOP.getSprite(this.spritePathDir + "backgrounds/iceBlue.png"));
           // listItems.add(new ListElement(le));
            scrollBody.add(listItems.get(listItems.size() - 1).getElement(), getConstraints());
        }
    }

    private void testLineLayout() {
        for (int x = 0; x < 20; x++) {
            RowListElement le = new RowListElement("This is element number " + (x + 1), this.spritePathDir + "backgrounds/iceBlue.png", 100, 90);

            listItems.add(new ListElement(le, this));
            scrollBody.add(listItems.get(listItems.size() - 1).getElement(), getConstraints());
        }
    }

    public void listFilesForFolder(final File folder) {
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesForFolder(fileEntry);
            } else {
                System.out.println(fileEntry.getName());
            }
        }
    }


}
