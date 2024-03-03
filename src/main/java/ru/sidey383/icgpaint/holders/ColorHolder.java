package ru.sidey383.icgpaint.holders;

import org.jetbrains.annotations.NotNull;

import java.awt.*;

public interface ColorHolder {

    @NotNull
    Color getColor();

    void setColor(@NotNull Color color);

    void addListener(ColorUpdateListener listener);

    void removeListener(ColorUpdateListener listener);

}
