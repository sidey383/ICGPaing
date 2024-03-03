package ru.sidey383.icgpaint.iteraction;

import org.jetbrains.annotations.NotNull;
import ru.sidey383.icgpaint.holders.ColorHolder;
import ru.sidey383.icgpaint.holders.ImageHolder;
import ru.sidey383.icgpaint.iteraction.color.ColorInteractions;
import ru.sidey383.icgpaint.iteraction.image.ImageInteractions;

public class UserInteractions {

    @NotNull
    private final ColorInteractions colorInteractions;

    @NotNull
    private final ImageInteractions imageInteractions;

    public UserInteractions(@NotNull ColorHolder colorHolder, @NotNull ImageHolder imageHolder) {
        this.colorInteractions = new ColorInteractions(colorHolder);
        this.imageInteractions = new ImageInteractions(imageHolder);
    }

    @NotNull
    public ColorInteractions getColorInteractions() {
        return colorInteractions;
    }

    @NotNull
    public ImageInteractions getImageInteractions() {
        return imageInteractions;
    }
}
