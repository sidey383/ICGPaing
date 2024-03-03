package ru.sidey383.icgpaint.toolbar.image;

import org.jetbrains.annotations.NotNull;
import ru.sidey383.icgpaint.iteraction.image.LoadImageInteraction;
import ru.sidey383.icgpaint.toolbar.BufferedImageIconSetter;
import ru.sidey383.icgpaint.toolbar.PaintToolbar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoadButton  extends JButton implements ActionListener {

    private final Dimension size = new Dimension(40, 40);

    @NotNull
    private final LoadImageInteraction loadImageInteraction;

    protected LoadButton(@NotNull LoadImageInteraction loadImageInteraction) {
        this.loadImageInteraction = loadImageInteraction;
        addActionListener(this);
        setToolTipText("Load image from file");
        new BufferedImageIconSetter(this,  PaintToolbar.loadIcon("/icon/load.png"));
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

    @Override
    public void actionPerformed(ActionEvent e) {
        loadImageInteraction.apply(this);
    }
}
