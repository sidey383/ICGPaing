package ru.sidey383.icgpaint.iteraction.image;

import org.jetbrains.annotations.NotNull;
import ru.sidey383.icgpaint.holders.ImageHolder;
import ru.sidey383.icgpaint.iteraction.ComponentInteraction;

import java.awt.*;

public class ClearImageInteraction implements ComponentInteraction {

    @NotNull
    private final ImageHolder imageHolder;

    public ClearImageInteraction(@NotNull ImageHolder imageHolder) {
        this.imageHolder = imageHolder;
    }

    public void apply(Component component) {
        imageHolder.clearImage();
    }

}
