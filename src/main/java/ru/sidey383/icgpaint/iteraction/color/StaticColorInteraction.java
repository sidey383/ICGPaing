package ru.sidey383.icgpaint.iteraction.color;

import org.jetbrains.annotations.NotNull;
import ru.sidey383.icgpaint.holders.ColorHolder;

import java.awt.*;

public class StaticColorInteraction {

    @NotNull
    private final ColorHolder colorHolder;

    @NotNull
    private final Color color;

    public StaticColorInteraction(@NotNull ColorHolder colorHolder, @NotNull Color color) {
        this.colorHolder = colorHolder;
        this.color = color;
    }

    @NotNull
    public ColorHolder getColorHolder() {
        return colorHolder;
    }

    @NotNull
    public Color getColor() {
        return color;
    }

    public void applyColor() {
        colorHolder.setColor(color);
    }

}
