package ru.sidey383.icgpaint.toolbar.color;

import ru.sidey383.icgpaint.iteraction.color.ColorInteractions;

import javax.swing.*;
import java.awt.*;
import java.util.Collection;
import java.util.List;

public class ColorButtonGroup extends ButtonGroup {

    private final List<StaticColorButton> staticStaticColorButtons;

    private final ChooseColorButton chooseColorButton;

    public ColorButtonGroup(ColorInteractions interactions) {
        staticStaticColorButtons = List.of(
                new StaticColorButton(interactions.getStaticColorInteraction(Color.BLACK), "Set the drawing color to black"),
                new StaticColorButton(interactions.getStaticColorInteraction(Color.WHITE), "Set the drawing color to white"),
                new StaticColorButton(interactions.getStaticColorInteraction(Color.GRAY), "Set the drawing color to gray"),
                new StaticColorButton(interactions.getStaticColorInteraction(Color.RED), "Set the drawing color to red"),
                new StaticColorButton(interactions.getStaticColorInteraction(Color.GREEN), "Set the drawing color to green"),
                new StaticColorButton(interactions.getStaticColorInteraction(Color.BLUE), "Set the drawing color to blue"),
                new StaticColorButton(interactions.getStaticColorInteraction(Color.YELLOW), "Set the drawing color to yellow"),
                new StaticColorButton(interactions.getStaticColorInteraction(Color.MAGENTA), "Set the drawing color to magenta"),
                new StaticColorButton(interactions.getStaticColorInteraction(Color.CYAN), "Set the drawing color to cyan")
        );
        staticStaticColorButtons.forEach(this::add);
        chooseColorButton = new ChooseColorButton(interactions.getChooseColorInteraction(), "Show current color, click to open color choose menu");
    }

    public Collection<StaticColorButton> getButtons() {
        return staticStaticColorButtons;
    }

    public ChooseColorButton getStaticColorButtons() {
        return chooseColorButton;
    }

}
