package ru.sidey383.icgpaint.toolbar.tools;

import org.jetbrains.annotations.NotNull;
import ru.sidey383.icgpaint.holders.DrawToolHolder;
import ru.sidey383.icgpaint.toolbar.BufferedImageIconSetter;
import ru.sidey383.icgpaint.tools.DrawTool;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;

public class ToolButton extends JToggleButton implements ItemListener {

    @NotNull
    private final DrawToolHolder holder;
    @NotNull
    private final DrawTool tool;

    private final Dimension size = new Dimension(40, 40);


    public ToolButton(@NotNull DrawToolHolder holder, @NotNull DrawTool tool, @NotNull BufferedImage icon, @NotNull String description) {
        super();
        this.holder = holder;
        this.tool = tool;
        setToolTipText(description);
        addItemListener(this);
        new BufferedImageIconSetter(this, icon);
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
    public void itemStateChanged(ItemEvent e) {
        holder.setDrawTool(tool);
    }
}
