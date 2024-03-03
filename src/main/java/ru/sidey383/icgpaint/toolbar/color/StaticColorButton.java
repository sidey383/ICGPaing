package ru.sidey383.icgpaint.toolbar.color;

import org.jetbrains.annotations.NotNull;
import ru.sidey383.icgpaint.iteraction.color.StaticColorInteraction;
import ru.sidey383.icgpaint.toolbar.BufferedImageIconSetter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;

public class StaticColorButton extends JButton implements ActionListener {


    @NotNull
    private final StaticColorInteraction colorInteraction;

    private final Dimension size = new Dimension(40, 40);


    public StaticColorButton(@NotNull StaticColorInteraction colorInteraction, String description) {
        super();
        this.colorInteraction = colorInteraction;
        setToolTipText(description);
        BufferedImage icon = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_RGB);
        Graphics gr = icon.getGraphics();
        gr.setColor(colorInteraction.getColor());
        gr.fillRect(0, 0, size.width, size.height);
        gr.dispose();
        new BufferedImageIconSetter(this, icon);
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        colorInteraction.applyColor();
    }

    @NotNull
    public Color getColor() {
        return colorInteraction.getColor();
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
