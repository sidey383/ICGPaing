package ru.sidey383.icgpaint.holders;

import org.jetbrains.annotations.NotNull;

import java.awt.image.BufferedImage;

public interface ImageHolder {

    void setImage(@NotNull BufferedImage image);

    @NotNull
    BufferedImage getImage();

    void resizeField(int width, int height);

    void clearImage();

}
