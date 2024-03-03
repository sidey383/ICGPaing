package ru.sidey383.icgpaint;

import ru.sidey383.icgpaint.holders.ColorHolderImpl;
import ru.sidey383.icgpaint.holders.BaseDrawTools;
import ru.sidey383.icgpaint.iteraction.UserInteractions;
import ru.sidey383.icgpaint.menu.ProgramMenuBar;
import ru.sidey383.icgpaint.toolbar.PaintToolbar;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame{

    public static void main(String[] args) {
        new MainFrame();
    }

    public MainFrame() {
        super();
        setTitle("ICG Paint");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSizeAndPosition();
        setFocusable(true);

        ColorHolderImpl colorHolder = new ColorHolderImpl();
        BaseDrawTools baseDrawTools = new BaseDrawTools(colorHolder);

        GraphicPanel graphicPanel = new GraphicPanel(baseDrawTools);
        add(graphicPanel.getScrollPane());

        UserInteractions userInteractions = new UserInteractions(colorHolder, graphicPanel, baseDrawTools);

        add(new PaintToolbar(userInteractions), BorderLayout.NORTH);

        setJMenuBar(new ProgramMenuBar(userInteractions));
        pack();

        setVisible(true);
    }

    private void setSizeAndPosition() {
        setMinimumSize(new Dimension(640, 480));
        setPreferredSize(new Dimension(800, 800));
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(screenSize.width / 2 - 400, screenSize.height / 2 - 400);
    }

}
