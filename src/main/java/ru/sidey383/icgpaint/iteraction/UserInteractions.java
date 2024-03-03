package ru.sidey383.icgpaint.iteraction;

import org.jetbrains.annotations.NotNull;
import ru.sidey383.icgpaint.holders.BaseDrawTools;
import ru.sidey383.icgpaint.holders.ColorHolder;
import ru.sidey383.icgpaint.holders.ImageHolder;
import ru.sidey383.icgpaint.iteraction.color.ColorInteractions;
import ru.sidey383.icgpaint.iteraction.image.ImageInteractions;
import ru.sidey383.icgpaint.iteraction.tool.DrawToolInteractions;

public class UserInteractions {

    @NotNull
    private final ColorInteractions colorInteractions;

    @NotNull
    private final ImageInteractions imageInteractions;

    @NotNull
    private final DrawToolInteractions drawToolInteractions;

    public UserInteractions(@NotNull ColorHolder colorHolder, @NotNull ImageHolder imageHolder, @NotNull BaseDrawTools baseDrawTools) {
        this.colorInteractions = new ColorInteractions(colorHolder);
        this.imageInteractions = new ImageInteractions(imageHolder);
        this.drawToolInteractions = new DrawToolInteractions(baseDrawTools);
    }

    @NotNull
    public ColorInteractions getColorInteractions() {
        return colorInteractions;
    }

    @NotNull
    public ImageInteractions getImageInteractions() {
        return imageInteractions;
    }

    @NotNull
    public DrawToolInteractions getDrawToolInteractions() {
        return drawToolInteractions;
    }
}
