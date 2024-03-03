package ru.sidey383.icgpaint.toolbar.tools;

import org.jetbrains.annotations.NotNull;
import ru.sidey383.icgpaint.holders.DrawToolHolder;
import ru.sidey383.icgpaint.toolbar.BufferedImageIconSetter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

public class ToolSettingsButton extends JButton implements ActionListener, WindowListener {

    private final Dimension size = new Dimension(40, 40);

    private final DrawToolHolder drawToolHolder;

    private JDialog dialog;

    public ToolSettingsButton(@NotNull DrawToolHolder drawToolHolder, @NotNull BufferedImage image) {
        this.drawToolHolder = drawToolHolder;
        new BufferedImageIconSetter(this, image);
        setToolTipText("Open settings for current instrument");
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
    public void actionPerformed(ActionEvent e) {
        if (dialog != null)
            return;
        JDialog dialog = drawToolHolder.getDrawTool().editDialog();
        if (dialog == null)
            return;
        Point p = getLocationOnScreen();
        dialog.setLocation(p);
        dialog.setAlwaysOnTop(true);
        dialog.addWindowListener(this);
        dialog.setVisible(true);
        this.dialog = dialog;
    }

    @Override
    public void windowOpened(WindowEvent e) {}

    @Override
    public void windowClosing(WindowEvent e) {
        dialog = null;
    }

    @Override
    public void windowClosed(WindowEvent e) {
        dialog = null;
    }

    @Override
    public void windowIconified(WindowEvent e) {}

    @Override
    public void windowDeiconified(WindowEvent e) {}

    @Override
    public void windowActivated(WindowEvent e) {}

    @Override
    public void windowDeactivated(WindowEvent e) {}

}
