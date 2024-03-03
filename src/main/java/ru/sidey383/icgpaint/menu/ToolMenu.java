package ru.sidey383.icgpaint.menu;

import org.jetbrains.annotations.NotNull;
import ru.sidey383.icgpaint.iteraction.tool.DrawToolInteractions;

import javax.swing.*;

public class ToolMenu extends JMenu {

    public ToolMenu(@NotNull DrawToolInteractions interactions) {
        super("Tools");
        JMenuItem lineItem = new JMenuItem("Line");
        lineItem.addActionListener((e) -> interactions.getLineDrawToolInteraction().apply(this));
        add(lineItem);
        JMenuItem fillItem = new JMenuItem("Fill");
        fillItem.addActionListener((e) -> interactions.getFillDrawToolInteraction().apply(this));
        add(fillItem);
        JMenuItem stampItem = new JMenuItem("Stamp");
        stampItem.addActionListener((e) -> interactions.getStampDrawToolInteraction().apply(this));
        add(stampItem);
        JMenuItem settingsItem = new JMenuItem("Settings");
        settingsItem.addActionListener((e) -> interactions.getDrawToolSettingInteraction().apply(this));
        add(settingsItem);
    }

}
