package ru.sidey383.icgpaint.toolbar.image;

import org.jetbrains.annotations.NotNull;
import ru.sidey383.icgpaint.iteraction.image.ImageInteractions;
import ru.sidey383.icgpaint.toolbar.InteractionJButton;

import javax.swing.*;
import java.util.Collection;
import java.util.List;

public class ImageEditGroup {

    private final AbstractButton saveButton;

    private final AbstractButton loadButton;

    private final AbstractButton clearButton;

    private final AbstractButton resizeButton;

    public ImageEditGroup(@NotNull ImageInteractions imageInteractions) {
        this.saveButton = new InteractionJButton<>(imageInteractions.getSaveImageInteraction(), "/icon/save.png", "Save image to file");
        this.loadButton = new InteractionJButton<>(imageInteractions.getLoadImageInteraction(), "/icon/load.png", "Load image from file");
        this.clearButton = new InteractionJButton<>(imageInteractions.getClearImageInteraction(), "/icon/clear.png", "Clear image");
        this.resizeButton = new InteractionJButton<>(imageInteractions.getResizeInteraction(), "/icon/resize.png", "Resize current image");
    }

    public Collection<AbstractButton> getButtons() {
        return List.of(saveButton, loadButton, clearButton, resizeButton);
    }

}
