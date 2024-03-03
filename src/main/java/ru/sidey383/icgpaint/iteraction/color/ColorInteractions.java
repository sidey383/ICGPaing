package ru.sidey383.icgpaint.iteraction.color;

import org.jetbrains.annotations.NotNull;
import ru.sidey383.icgpaint.holders.ColorHolder;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class ColorInteractions {

    @NotNull
    private final Map<Color, StaticColorInteraction> staticColorInteractions = new HashMap<>();

    @NotNull
    private final ChooseColorInteraction chooseColorInteraction;

    @NotNull
    private final ColorHolder colorHolder;

    public ColorInteractions(@NotNull ColorHolder colorHolder) {
        this.colorHolder = colorHolder;
        this.chooseColorInteraction = new ChooseColorInteraction(colorHolder);
    }

    @NotNull
    public StaticColorInteraction getStaticColorInteraction(Color color) {
        return staticColorInteractions.computeIfAbsent(color, (c) -> new StaticColorInteraction(colorHolder, c));
    }

    @NotNull
    public ChooseColorInteraction getChooseColorInteraction() {
        return chooseColorInteraction;
    }

}
