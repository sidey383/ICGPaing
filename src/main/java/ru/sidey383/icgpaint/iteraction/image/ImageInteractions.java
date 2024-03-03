package ru.sidey383.icgpaint.iteraction.image;

import org.jetbrains.annotations.NotNull;
import ru.sidey383.icgpaint.holders.ImageHolder;

import javax.swing.*;

public class ImageInteractions {

    @NotNull
    private final SaveImageInteraction saveImageInteraction;

    @NotNull
    private final LoadImageInteraction loadImageInteraction;

    @NotNull
    private final ClearImageInteraction clearImageInteraction;

    public ImageInteractions(@NotNull ImageHolder holder) {
        this.saveImageInteraction = new SaveImageInteraction(holder);
        this.loadImageInteraction = new LoadImageInteraction(holder);
        this.clearImageInteraction = new ClearImageInteraction(holder);
    }

    public static void showErrorDialog(String message) {
        JDialog dialog = new JDialog();
        dialog.setTitle("Error");
        dialog.add(new JLabel(message));
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
}
