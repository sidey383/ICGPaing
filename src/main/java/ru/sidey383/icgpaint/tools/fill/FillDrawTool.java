package ru.sidey383.icgpaint.tools.fill;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.sidey383.icgpaint.holders.ColorHolder;
import ru.sidey383.icgpaint.tools.DrawTool;

import javax.swing.*;
import java.awt.image.BufferedImage;
import java.util.Stack;

public class FillDrawTool extends DrawTool {

    @NotNull
    private final ColorHolder colorHolder;

    public FillDrawTool(@NotNull ColorHolder colorHolder) {
        this.colorHolder = colorHolder;
    }

    private record Span(int x0, int x1, int y) {}

    private Span getSpan(BufferedImage image, int color, int x, int y) {
        int x0 = x;
        int x1 = x;
        while (x0 > 1) {
            if (image.getRGB(x0 - 1, y) == color) {
                x0--;
            } else {
                break;
            }
        }
        while (x1 < image.getWidth() - 1) {
            if (image.getRGB(x1 + 1, y) == color) {
                x1++;
            } else {
                break;
            }
        }
        return new Span(x0, x1, y);
    }

    private void foundSpans(Stack<Span> stack, BufferedImage image, int color, int x0, int x1, int y) {
        if (y >= image.getHeight() || y < 0)
            return;
        if (x0 < 0)
            x0 = 0;
        if (x1 >= image.getWidth())
            x1 = image.getWidth() - 1;
        int xi = x0;
        while (xi <= x1) {
            if (image.getRGB(xi, y) == color) {
                Span s = getSpan(image, color, xi, y);
                stack.push(s);
                xi = s.x1() + 1;
            }
            xi++;
        }
    }

    private void spanFill(BufferedImage image, int newColor, int x, int y) {
        if (x >= image.getWidth() || x < 0)
            return;
        if (y >= image.getHeight() || y < 0)
            return;
        int oldColor = image.getRGB(x, y);
        if (newColor == oldColor)
            return;
        Span initSpan = getSpan(image, oldColor, x, y);
        Stack<Span> stack = new Stack<>();
        stack.push(initSpan);
        while (!stack.isEmpty()) {
            Span s = stack.pop();
            for (int i = s.x0(); i <= s.x1(); i++) {
                image.setRGB(i, s.y(), newColor);
            }
            foundSpans(stack, image, oldColor, s.x0(), s.x1(), s.y() - 1);
            foundSpans(stack, image, oldColor, s.x0(), s.x1(), s.y() + 1);
        }
    }

    private void fill(int x, int y) {
        BufferedImage image = image();
        if (image == null)
            return;
        spanFill(image, colorHolder.getColor().getRGB(), x, y);
        updateImage();
    }

    @Override
    public @Nullable JDialog editDialog() {
        return null;
    }

    @Override
    public void click(int x, int y, int button) {
        fill(x, y);
    }

    @Override
    public void press(int x, int y, int button) {
        fill(x, y);
    }
}
