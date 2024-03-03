package ru.sidey383.icgpaint.tools.dialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

public class TerminatePart extends JPanel {

    public TerminatePart(JDialog dialog, Runnable undo) {
        setLayout(new FlowLayout(FlowLayout.CENTER));
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener((e) -> {
            undo.run();
            dialog.dispatchEvent(new WindowEvent(dialog, WindowEvent.WINDOW_CLOSING));
        });
        JButton applyButton = new JButton("Apply");
        applyButton.addActionListener((e) -> {
            dialog.dispatchEvent(new WindowEvent(dialog, WindowEvent.WINDOW_CLOSING));
        });
        add(cancelButton);
        add(applyButton);
    }

}
