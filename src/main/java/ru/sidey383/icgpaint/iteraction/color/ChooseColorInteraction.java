package ru.sidey383.icgpaint.iteraction.color;

import org.jetbrains.annotations.NotNull;
import ru.sidey383.icgpaint.holders.ColorHolder;
import ru.sidey383.icgpaint.holders.ColorUpdateListener;
import ru.sidey383.icgpaint.iteraction.ComponentInteraction;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class ChooseColorInteraction implements ColorUpdateListener, ChangeListener, ComponentInteraction {

    @NotNull
    private final JColorChooser colorChooser;

    @NotNull
    private final ColorHolder colorHolder;

    @NotNull
    private final JDialog dialog;

    public ChooseColorInteraction(@NotNull ColorHolder colorHolder) {
        this.colorHolder = colorHolder;
        colorHolder.addListener(this);
        this.colorChooser = new JColorChooser();
        this.colorChooser.setColor(colorHolder.getColor());
        this.colorChooser.getSelectionModel().addChangeListener(this);
        this.dialog = new JDialog();
        this.dialog.add(this.colorChooser);
        this.dialog.pack();
    }

    @Override
    public void apply(Component parent) {
        Point p = parent.getLocationOnScreen();
        dialog.setLocation(p);
        dialog.setAlwaysOnTop(true);
        dialog.setVisible(true);
    }

    @NotNull
    public ColorHolder getColorHolder() {
        return colorHolder;
    }

    @Override
    public void onColorUpdate(Color color) {
        if (!colorChooser.getColor().equals(color))
            colorChooser.setColor(color);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        Color color = colorChooser.getColor();
        colorHolder.setColor(color);
    }
}
