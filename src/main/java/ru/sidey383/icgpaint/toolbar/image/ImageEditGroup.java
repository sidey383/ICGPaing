package ru.sidey383.icgpaint.toolbar.image;

import org.jetbrains.annotations.NotNull;
import ru.sidey383.icgpaint.iteraction.image.ImageInteractions;

import javax.swing.*;
import java.util.Collection;
import java.util.List;

public class ImageEditGroup {

    private final SaveButton saveButton;

    private final LoadButton loadButton;

    private final ClearButton clearButton;

    public ImageEditGroup(@NotNull ImageInteractions imageInteractions) {
        this.saveButton = new SaveButton(imageInteractions.getSaveImageInteraction());
        this.loadButton = new LoadButton(imageInteractions.getLoadImageInteraction());
        this.clearButton = new ClearButton(imageInteractions.getClearImageInteraction());
    }

    public Collection<JComponent> getButtons() {
        return List.of(saveButton, loadButton, clearButton);
    }

}
