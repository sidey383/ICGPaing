package ru.sidey383.icgpaint.toolbar.image;

import org.jetbrains.annotations.NotNull;
import ru.sidey383.icgpaint.holders.ImageHolder;

import javax.swing.*;
import java.util.Collection;
import java.util.List;

public class ImageEditGroup {

    private final SaveButton saveButton;

    private final LoadButton loadButton;

    public ImageEditGroup(@NotNull ImageHolder holder) {
        this.saveButton = new SaveButton(holder);
        this.loadButton = new LoadButton(holder);
    }

    public Collection<JComponent> getButtons() {
        return List.of(saveButton, loadButton);
    }

    public static void showErrorDialog(String message) {
        JDialog dialog = new JDialog();
        dialog.setTitle("Error");
        dialog.add(new JLabel(message));
        dialog.pack();
        dialog.setAlwaysOnTop(true);
        dialog.setVisible(true);
    }

}
