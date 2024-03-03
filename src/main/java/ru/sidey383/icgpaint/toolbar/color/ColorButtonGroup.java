package ru.sidey383.icgpaint.toolbar.color;

import ru.sidey383.icgpaint.iteraction.color.ColorInteractions;
import ru.sidey383.icgpaint.toolbar.InteractionJButton;

import javax.swing.*;
import java.awt.*;
import java.util.Collection;
import java.util.List;

public class ColorButtonGroup {

    private final List<AbstractButton> staticStaticColorButtons;

    private final ChooseColorButton chooseColorButton;

    public ColorButtonGroup(ColorInteractions interactions) {
        staticStaticColorButtons = List.of(
                new InteractionJButton<>(interactions.getStaticColorInteraction(Color.BLACK), Color.BLACK, "Set the drawing color to black"),
                new InteractionJButton<>(interactions.getStaticColorInteraction(Color.WHITE), Color.WHITE, "Set the drawing color to white"),
                new InteractionJButton<>(interactions.getStaticColorInteraction(Color.GRAY), Color.GRAY, "Set the drawing color to gray"),
                new InteractionJButton<>(interactions.getStaticColorInteraction(Color.RED), Color.RED, "Set the drawing color to red"),
                new InteractionJButton<>(interactions.getStaticColorInteraction(Color.GREEN), Color.GREEN, "Set the drawing color to green"),
                new InteractionJButton<>(interactions.getStaticColorInteraction(Color.BLUE), Color.BLUE, "Set the drawing color to blue"),
                new InteractionJButton<>(interactions.getStaticColorInteraction(Color.YELLOW), Color.YELLOW, "Set the drawing color to yellow"),
                new InteractionJButton<>(interactions.getStaticColorInteraction(Color.MAGENTA), Color.MAGENTA, "Set the drawing color to magenta"),
                new InteractionJButton<>(interactions.getStaticColorInteraction(Color.CYAN), Color.CYAN, "Set the drawing color to cyan")
        );
        chooseColorButton = new ChooseColorButton(interactions.getChooseColorInteraction(), "Show current color, click to open color choose menu");
    }

    public Collection<AbstractButton> getButtons() {
        return staticStaticColorButtons;
    }

    public ChooseColorButton getStaticColorButtons() {
        return chooseColorButton;
    }

}
