package ru.sidey383.icgpaint.menu;

import org.jetbrains.annotations.NotNull;
import ru.sidey383.icgpaint.iteraction.UserInteractions;

import javax.swing.*;

public class ProgramMenuBar extends JMenuBar {

    public ProgramMenuBar(@NotNull UserInteractions userInteractions) {
        add(new ImageMenu(userInteractions.getImageInteractions()));
        add(new ColorMenu(userInteractions.getColorInteractions()));
        add(new ToolMenu(userInteractions.getDrawToolInteractions()));
        add(new AboutItem(userInteractions.getAboutInteraction()));
    }

}
