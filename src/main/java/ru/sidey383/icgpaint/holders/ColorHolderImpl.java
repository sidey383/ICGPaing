package ru.sidey383.icgpaint.holders;

import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public class ColorHolderImpl implements ColorHolder {

    @NotNull
    private Color color = Color.BLACK;

    private final Set<ColorUpdateListener> listeners = new HashSet<>();

    public ColorHolderImpl() {}

    @NotNull
    public Color getColor() {
        return color;
    }

    public void setColor(@NotNull Color color) {
        this.color = color;
        listeners.forEach((l) -> {
            try {
                l.onColorUpdate(color);
            } catch (Throwable t) {
                t.printStackTrace(System.err);
            }
        });
    }

    @Override
    public void addListener(ColorUpdateListener listener) {
        listeners.add(listener);
    }

    @Override
    public void removeListener(ColorUpdateListener listener) {
        listeners.remove(listener);
    }


}
