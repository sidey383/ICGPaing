package ru.sidey383.icgpaint.toolbar.image;

import org.jetbrains.annotations.NotNull;
import ru.sidey383.icgpaint.holders.ImageHolder;
import ru.sidey383.icgpaint.toolbar.BufferedImageIconSetter;
import ru.sidey383.icgpaint.toolbar.PaintToolbar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClearButton extends JButton implements ActionListener {

    private final Dimension size = new Dimension(40, 40);

    @NotNull
    private final ImageHolder imageHolder;

    protected ClearButton(@NotNull ImageHolder imageHolder) {
        this.imageHolder = imageHolder;
        addActionListener(this);
        setToolTipText("Clear image");
        new BufferedImageIconSetter(this,  PaintToolbar.loadIcon("/icon/load.png"));
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        imageHolder.clearImage();
    }
}
