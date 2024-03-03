package ru.sidey383.icgpaint.toolbar.color;

import org.jetbrains.annotations.NotNull;
import ru.sidey383.icgpaint.holders.ColorUpdateListener;
import ru.sidey383.icgpaint.iteraction.color.ChooseColorInteraction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ChooseColorButton extends JButton implements ActionListener, ColorUpdateListener {

    private final Dimension size = new Dimension(40, 40);


    @NotNull
    private ChooseColorInteraction chooseColorInteraction;

    public ChooseColorButton(@NotNull ChooseColorInteraction chooseColorInteraction, String description) {
        this.chooseColorInteraction = chooseColorInteraction;
        setToolTipText(description);
        setBackground(chooseColorInteraction.getColorHolder().getColor());
        chooseColorInteraction.getColorHolder().addListener(this);
        addActionListener(this);
    }

    @Override
    public Dimension getPreferredSize() {
        return size;
    }

    @Override
    public Dimension getMaximumSize() {
        return size;
    }

    @Override
    public Dimension getMinimumSize() {
        return size;
    }

    @Override
    public void onColorUpdate(Color color) {
        setBackground(color);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        chooseColorInteraction.openMenu(this);
    }

}
