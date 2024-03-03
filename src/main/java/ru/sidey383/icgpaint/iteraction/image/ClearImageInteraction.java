package ru.sidey383.icgpaint.iteraction.image;

import org.jetbrains.annotations.NotNull;
import ru.sidey383.icgpaint.holders.ImageHolder;

public class ClearImageInteraction {

    @NotNull
    private final ImageHolder imageHolder;

    public ClearImageInteraction(@NotNull ImageHolder imageHolder) {
        this.imageHolder = imageHolder;
    }

    public void apply() {
        imageHolder.clearImage();
    }

}
