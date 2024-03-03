package ru.sidey383.icgpaint.tools;

import org.jetbrains.annotations.NotNull;

import java.awt.image.BufferedImage;

public record DrawToolContext(@NotNull BufferedImage image, @NotNull Runnable updateCallback) {

    public void update() {
        updateCallback.run();
    }

}
