package ru.sidey383.icgpaint.toolbar.image;

import org.jetbrains.annotations.NotNull;
import ru.sidey383.icgpaint.iteraction.image.ClearImageInteraction;
import ru.sidey383.icgpaint.toolbar.BufferedImageIconSetter;
import ru.sidey383.icgpaint.toolbar.PaintToolbar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClearButton extends JButton implements ActionListener {

    private final Dimension size = new Dimension(40, 40);

    @NotNull
    private final ClearImageInteraction clearImage;

    protected ClearButton(@NotNull ClearImageInteraction clearImage) {
        this.clearImage = clearImage;
        addActionListener(this);
        setToolTipText("Clear image");
        new BufferedImageIconSetter(this,  PaintToolbar.loadIcon("/icon/clear.png"));
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
        clearImage.apply();
    }
}
