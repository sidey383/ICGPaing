package ru.sidey383.icgpaint.menu;

import ru.sidey383.icgpaint.iteraction.AboutInteraction;

import javax.swing.*;
import java.awt.*;

public class AboutItem extends JMenuItem {

    public AboutItem(AboutInteraction about) {
        super("About");
        setMaximumSize(new Dimension(45, 60));
        addActionListener((a) -> about.apply(this));
    }

}
