package ru.sidey383.icgpaint.iteraction.color;

import org.jetbrains.annotations.NotNull;
import ru.sidey383.icgpaint.holders.ColorHolder;
import ru.sidey383.icgpaint.iteraction.ComponentInteraction;

import java.awt.*;

public record StaticColorInteraction(@NotNull ColorHolder colorHolder, @NotNull Color color) implements ComponentInteraction {

    @Override
    public void apply(Component component) {
        colorHolder.setColor(color);
    }

}
