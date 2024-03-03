package ru.sidey383.icgpaint.iteraction.image;

import org.jetbrains.annotations.NotNull;
import ru.sidey383.icgpaint.holders.ImageHolder;

import javax.swing.*;
import java.awt.*;

public class ImageInteractions {

    @NotNull
    private final SaveImageInteraction saveImageInteraction;

    @NotNull
    private final LoadImageInteraction loadImageInteraction;

    @NotNull
    private final ClearImageInteraction clearImageInteraction;

    @NotNull
    private final ResizeInteraction resizeInteraction;

    public ImageInteractions(@NotNull ImageHolder holder) {
        this.saveImageInteraction = new SaveImageInteraction(holder);
        this.loadImageInteraction = new LoadImageInteraction(holder);
        this.clearImageInteraction = new ClearImageInteraction(holder);
        this.resizeInteraction = new ResizeInteraction(holder);
    }

    public static void showErrorDialog(String message) {
        JDialog dialog = new JDialog();
        dialog.setTitle("Error");
        dialog.setPreferredSize(new Dimension(400, 100));
        dialog.setMinimumSize(new Dimension(400, 100));
        dialog.add(new JLabel(message), BorderLayout.CENTER);
        dialog.pack();
        dialog.setAlwaysOnTop(true);
        dialog.setVisible(true);
    }

    @NotNull
    public SaveImageInteraction getSaveImageInteraction() {
        return saveImageInteraction;
    }

    @NotNull
    public LoadImageInteraction getLoadImageInteraction() {
        return loadImageInteraction;
    }

    @NotNull
    public ClearImageInteraction getClearImageInteraction() {
        return clearImageInteraction;
    }

    @NotNull
    public ResizeInteraction getResizeInteraction() {
        return resizeInteraction;
    }

}
