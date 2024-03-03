package ru.sidey383.icgpaint.toolbar;

import org.jetbrains.annotations.NotNull;
import ru.sidey383.icgpaint.iteraction.UserInteractions;
import ru.sidey383.icgpaint.toolbar.color.ColorButtonGroup;
import ru.sidey383.icgpaint.toolbar.image.ImageEditGroup;
import ru.sidey383.icgpaint.toolbar.tools.ToolButtonGroup;

import javax.swing.*;

public class PaintToolbar extends JToolBar {

    public PaintToolbar(@NotNull UserInteractions interactions) {
        @NotNull ImageEditGroup imageEditGroup = new ImageEditGroup(interactions.getImageInteractions());
        @NotNull ToolButtonGroup toolButtonGroup = new ToolButtonGroup(interactions.getDrawToolInteractions());
        @NotNull ColorButtonGroup colorButtonGroup = new ColorButtonGroup(interactions.getColorInteractions());
        imageEditGroup.getButtons().forEach(this::add);
        toolButtonGroup.getToolsButtons().forEach(this::add);
        colorButtonGroup.getButtons().forEach(this::add);
        add(colorButtonGroup.getStaticColorButtons());
        add(toolButtonGroup.getToolSettingsButton());
    }

}
