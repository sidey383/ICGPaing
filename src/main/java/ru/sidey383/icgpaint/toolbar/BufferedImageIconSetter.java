package ru.sidey383.icgpaint.toolbar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;

public class BufferedImageIconSetter extends ComponentAdapter {

    private final BufferedImage image;

    public BufferedImageIconSetter(AbstractButton button, BufferedImage image) {
        this.image = image;
        button.addComponentListener(this);
    }

    @Override
    public void componentResized(ComponentEvent e) {
        AbstractButton btn = (AbstractButton) e.getComponent();
        Dimension size = btn.getSize();
        Insets insets = btn.getInsets();
        size.width -= insets.left + insets.right;
        size.height -= insets.top + insets.bottom;
        if (size.width > size.height) {
            size.width = -1;
        } else {
            size.height = -1;
        }
        Image scaled = image.getScaledInstance(size.width, size.height, java.awt.Image.SCALE_SMOOTH);
        btn.setIcon(new ImageIcon(scaled));
    }

}
