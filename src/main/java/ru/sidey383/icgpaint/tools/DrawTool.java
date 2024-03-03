package ru.sidey383.icgpaint.tools;

import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.image.BufferedImage;

public abstract class DrawTool {

    @Nullable
    private DrawToolContext context;

    public synchronized void setToolContext(@Nullable DrawToolContext context) {
        this.context = context;
    }

    public synchronized void updateImage() {
        if (context != null)
            context.update();
    }

    @Nullable
    public synchronized BufferedImage image() {
        if (context == null)
            return null;
        return context.image();
    }

    @Nullable
    public abstract JDialog editDialog();

    public void click(int x, int y, int button) {}

    public void press(int x, int y, int button) {}

    public void drag(int x, int y, int button) {}

    public void realise(int x, int y, int button) {}

}
