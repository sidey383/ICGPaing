package ru.sidey383.icgpaint.iteraction.image;

import org.jetbrains.annotations.NotNull;
import ru.sidey383.icgpaint.dialog.NumberChoosePart;
import ru.sidey383.icgpaint.holders.ImageHolder;
import ru.sidey383.icgpaint.iteraction.ComponentInteraction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.util.concurrent.atomic.AtomicInteger;

public class ResizeInteraction implements ComponentInteraction {

    private final ImageHolder holder;

    private JDialog dialog;

    public ResizeInteraction(@NotNull ImageHolder holder) {
        this.holder = holder;
    }

    @Override
    public void apply(Component component) {
        if (dialog != null && dialog.isActive())
            return;
        dialog = new JDialog();
        AtomicInteger width = new AtomicInteger(holder.getImage().getWidth());
        AtomicInteger height = new AtomicInteger(holder.getImage().getHeight());
        NumberChoosePart widthChoose = new NumberChoosePart("Width", 100, 10000, holder.getImage().getWidth()) {
            @Override
            protected void setValue(int val) {
                width.set(val);
            }
        };
        NumberChoosePart heightChoose = new NumberChoosePart("Height", 100, 10000, holder.getImage().getHeight()) {
            @Override
            protected void setValue(int val) {
                height.set(val);
            }
        };
        JButton applyButton = new JButton("Resize");
        applyButton.addActionListener((e) -> {
            holder.resizeField(width.get(), height.get());
            dialog.dispatchEvent(new WindowEvent(dialog, WindowEvent.WINDOW_CLOSING));
        });
        dialog.setTitle("Resize window");
        dialog.getContentPane().setLayout(new BoxLayout(dialog.getContentPane(), BoxLayout.Y_AXIS));
        dialog.getContentPane().add(widthChoose);
        dialog.getContentPane().add(heightChoose);
        dialog.getContentPane().add(applyButton);
        dialog.pack();
        dialog.setAlwaysOnTop(true);
        dialog.setMinimumSize(dialog.getSize());
        dialog.setVisible(true);
    }

}
