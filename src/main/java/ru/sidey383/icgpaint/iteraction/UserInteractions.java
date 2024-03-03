package ru.sidey383.icgpaint.iteraction;

import org.jetbrains.annotations.NotNull;
import ru.sidey383.icgpaint.holders.BaseDrawTools;
import ru.sidey383.icgpaint.holders.ColorHolder;
import ru.sidey383.icgpaint.holders.ImageHolder;
import ru.sidey383.icgpaint.iteraction.color.ColorInteractions;

public class UserInteractions {

    @NotNull
    private final ColorInteractions colorInteractions;

    public UserInteractions(@NotNull ColorHolder colorHolder, @NotNull BaseDrawTools drawToolContext, @NotNull ImageHolder imageHolder) {
        this.colorInteractions = new ColorInteractions(colorHolder);
    }

    @NotNull
    public ColorInteractions getColorInteractions() {
        return colorInteractions;
    }

}
