package ru.sidey383.icgpaint.toolbar.image;

import org.jetbrains.annotations.NotNull;
import ru.sidey383.icgpaint.iteraction.image.SaveImageInteraction;
import ru.sidey383.icgpaint.toolbar.BufferedImageIconSetter;
import ru.sidey383.icgpaint.toolbar.PaintToolbar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SaveButton extends JButton implements ActionListener {

    private final Dimension size = new Dimension(40, 40);

    @NotNull
    private final SaveImageInteraction imageInteractions;

    protected SaveButton(@NotNull SaveImageInteraction imageInteraction) {
        this.imageInteractions = imageInteraction;
        addActionListener(this);
        setToolTipText("Save image to file");
        new BufferedImageIconSetter(this,  PaintToolbar.loadIcon("/icon/save.png"));
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        imageInteractions.apply(this);
    }

    @Override
    public Dimension getPreferredSize() {
        return size;
    }

    @Override
    public Dimension getMaximumSize() {
        return size;
    }

    @Override
    public Dimension getMinimumSize() {
        return size;
    }

}
