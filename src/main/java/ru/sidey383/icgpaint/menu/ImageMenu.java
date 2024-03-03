package ru.sidey383.icgpaint.menu;

import org.jetbrains.annotations.NotNull;
import ru.sidey383.icgpaint.iteraction.image.ImageInteractions;

import javax.swing.*;

public class ImageMenu extends JMenu {

    public ImageMenu(@NotNull ImageInteractions imageInteractions) {
        super("Image");

        JMenuItem saveItem = new JMenuItem("Save");
        saveItem.addActionListener((e) -> imageInteractions.getSaveImageInteraction().apply(saveItem));
        saveItem.setToolTipText("Save image to file");
        add(saveItem);


        JMenuItem loadItem = new JMenuItem("Load");
        loadItem.addActionListener((e) -> imageInteractions.getLoadImageInteraction().apply(loadItem));
        loadItem.setToolTipText("Load image from file");
        add(loadItem);

        JMenuItem clearItem = new JMenuItem("Clear");
        clearItem.addActionListener((e) -> imageInteractions.getClearImageInteraction().apply(this));
        clearItem.setToolTipText("Clear image");
        add(clearItem);
    }

}
