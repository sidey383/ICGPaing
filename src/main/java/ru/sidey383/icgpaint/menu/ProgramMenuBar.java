package ru.sidey383.icgpaint.menu;

import org.jetbrains.annotations.NotNull;
import ru.sidey383.icgpaint.holders.ImageHolder;
import ru.sidey383.icgpaint.iteraction.UserInteractions;

import javax.swing.*;

public class ProgramMenuBar extends JMenuBar {

    public ProgramMenuBar(@NotNull UserInteractions userInteractions, @NotNull ImageHolder imageHolder) {
        add(new ImageMenu(userInteractions.getImageInteractions()));
        add(new ColorMenu(userInteractions.getColorInteractions()));
    }

}
