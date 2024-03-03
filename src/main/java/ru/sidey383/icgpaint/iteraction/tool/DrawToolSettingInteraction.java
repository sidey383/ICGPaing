package ru.sidey383.icgpaint.iteraction.tool;

import org.jetbrains.annotations.NotNull;
import ru.sidey383.icgpaint.holders.DrawToolHolder;
import ru.sidey383.icgpaint.holders.DrawToolUpdateListener;
import ru.sidey383.icgpaint.iteraction.ComponentInteraction;
import ru.sidey383.icgpaint.tools.DrawTool;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class DrawToolSettingInteraction implements WindowListener, DrawToolUpdateListener, ComponentInteraction {

    @NotNull
    private final DrawToolHolder drawToolHolder;

    private DrawTool editedTool;

    private JDialog dialog;

    public DrawToolSettingInteraction(@NotNull DrawToolHolder drawToolHolder) {
        this.drawToolHolder = drawToolHolder;
        this.drawToolHolder.addListener(this);
    }

    @Override
    public void apply(Component parent) {
        if (dialog != null)
            return;
        this.editedTool = drawToolHolder.getDrawTool();
        JDialog dialog = this.editedTool.editDialog();
        if (dialog == null)
            return;
        Point p = parent.getLocationOnScreen();
        dialog.setLocation(p);
        dialog.setAlwaysOnTop(true);
        dialog.addWindowListener(this);
        dialog.setVisible(true);
        this.dialog = dialog;
    }

    @Override
    public void onDrawToolUpdate(DrawTool tool) {
        if (editedTool != null && dialog != null && tool != editedTool) {
            dialog.dispatchEvent(new WindowEvent(dialog, WindowEvent.WINDOW_CLOSING));
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {}

    @Override
    public void windowClosing(WindowEvent e) {
        dialog = null;
        editedTool = null;
    }

    @Override
    public void windowClosed(WindowEvent e) {
        dialog = null;
        editedTool = null;
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
