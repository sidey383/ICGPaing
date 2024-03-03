package ru.sidey383.icgpaint.menu;

import org.jetbrains.annotations.NotNull;
import ru.sidey383.icgpaint.iteraction.color.ColorInteractions;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Map;

public class ColorMenu extends JMenu {

    private static final Map<Color, String[]> staticColors = Map.of(
            Color.BLACK, new String[]{"Black", "Set the drawing color to black"},
            Color.WHITE, new String[]{"White","Set the drawing color to white"},
            Color.GRAY, new String[]{"Gray","Set the drawing color to gray"},
            Color.RED, new String[]{"Red","Set the drawing color to red"},
            Color.GREEN, new String[]{"Green","Set the drawing color to green"},
            Color.BLUE, new String[]{"Blue","Set the drawing color to blue"},
            Color.YELLOW, new String[]{"Yellow","Set the drawing color to yellow"},
            Color.MAGENTA, new String[]{"Magenta","Set the drawing color to magenta"},
            Color.CYAN, new String[]{"Cyan","Set the drawing color to cyan"}
    );

    public ColorMenu(@NotNull ColorInteractions interactions) {
        super("Color");
        
        JMenu staticColorMenu = new JMenu("Colors");
        for (Map.Entry<Color, String[]> e : staticColors.entrySet()) {
            JMenuItem item = new JMenuItem(e.getValue()[0]);
            item.setToolTipText(e.getValue()[1]);
            item.addActionListener((ev) -> interactions.getStaticColorInteraction(e.getKey()).applyColor());
            item.setIcon(new ImageIcon(getColoredImage(e.getKey())));
            staticColorMenu.add(item);
        }
        add(staticColorMenu);

        JMenuItem chooseItem = new JMenuItem("ChooseColor");
        chooseItem.addActionListener((e) -> interactions.getChooseColorInteraction().openMenu(this));
        chooseItem.setToolTipText("Open color choose menu");
        add(chooseItem);
    }

    private BufferedImage getColoredImage(Color color) {
        BufferedImage image = new BufferedImage(20, 20, BufferedImage.TYPE_INT_RGB);
        Graphics2D gr = image.createGraphics();
        gr.setColor(color);
        gr.fillRect(0, 0, 20, 20);
        gr.dispose();
        return image;
    }

}
