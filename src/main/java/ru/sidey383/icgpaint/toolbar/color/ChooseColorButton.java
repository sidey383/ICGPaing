package ru.sidey383.icgpaint.toolbar.color;

import org.jetbrains.annotations.NotNull;
import ru.sidey383.icgpaint.holders.ColorUpdateListener;
import ru.sidey383.icgpaint.iteraction.color.ChooseColorInteraction;
import ru.sidey383.icgpaint.toolbar.InteractionJButton;

import java.awt.*;

public class ChooseColorButton extends InteractionJButton<ChooseColorInteraction> implements ColorUpdateListener {


    public ChooseColorButton(@NotNull ChooseColorInteraction interaction, String description) {
        super(interaction, interaction.getColorHolder().getColor(), description);
        interaction.getColorHolder().addListener(this);
    }

    @Override
    public void onColorUpdate(Color color) {
        setColor(color);
    }

}
