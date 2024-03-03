package ru.sidey383.icgpaint.tools.line;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.sidey383.icgpaint.holders.ColorHolder;
import ru.sidey383.icgpaint.tools.DrawTool;
import ru.sidey383.icgpaint.dialog.NumberChoosePart;
import ru.sidey383.icgpaint.dialog.OptionChoosePart;
import ru.sidey383.icgpaint.dialog.TerminatePart;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;

public class LineDrawTool extends DrawTool implements LineDrawer {

    private final ColorHolder colorHolder;

    private int lineSize = 1;

    @NotNull
    private LineMode mode;

    @NotNull
    private final TwoClickLineMode twoClickLineMode;

    @NotNull
    private final LeftRightClickLineMode leftRightClickLineMode;

    @NotNull
    private final HoldLineMode holdLineMode;

    public LineDrawTool(ColorHolder colorHolder) {
        this.colorHolder = colorHolder;
        this.holdLineMode = new HoldLineMode(this);
        this.leftRightClickLineMode = new LeftRightClickLineMode(this);
        this.twoClickLineMode = new TwoClickLineMode(this);
        this.mode = twoClickLineMode;
    }

    @Override
    public void draw(int x0, int y0, int x1, int y1) {
        BufferedImage image = image();
        Color color = colorHolder.getColor();
        if (image == null)
            return;
        if (lineSize == 1) {
            runAlgorithm(image, color.getRGB(), x0, y0, x1, y1);
        } else {
            Graphics2D gr = image.createGraphics();
            gr.setColor(color);
            gr.setStroke(new BasicStroke(lineSize));
            gr.draw(new Line2D.Float(x0, y0, x1, y1));
            gr.dispose();
        }
        updateImage();
    }

    protected void runAlgorithm(BufferedImage image, int color, int x0, int y0, int x1, int y1) {

        final int dx = x1 - x0;
        final int dy = y1 - y0;
        final int dxAbs = Math.abs(dx);
        final int dyAbs = Math.abs(dy);

        int accretion = 0;

        if (dxAbs >= dyAbs) {
            int y = y0;
            final int dir = dy != 0 ? (dy > 0 ? 1 : -1) : 0;
            for (int x = x0; dx > 0 ? x <= x1 : x >= x1; x += dx > 0 ? 1 : -1) {
                setDot(image, color, x, y);
                accretion += dyAbs;
                if (accretion >= dxAbs) {
                    accretion -= dxAbs;
                    y += dir;
                }
            }
        } else {
            int x = x0;
            final int dir = dx != 0 ? (dx > 0 ? 1 : -1) : 0;
            for (int y = y0; dy > 0 ? y <= y1 : y >= y1; y += dy > 0 ? 1 : -1) {
                setDot(image, color, x, y);
                accretion += dxAbs;
                if (accretion >= dyAbs) {
                    accretion -= dyAbs;
                    x += dir;
                }
            }
        }
    }

    protected void setDot(BufferedImage image, int color, int x, int y) {
        if (x >= 0 && y >= 0 && x < image.getWidth() && y < image.getHeight())
            image.setRGB(x, y, color);
    }

    @Override
    public @Nullable JDialog editDialog() {
        JDialog dialog = new JDialog();
        OptionChoosePart typeChoose = new OptionChoosePart(
                new OptionChoosePart.Option("Two click") {
                    @Override
                    public void select() {
                        mode.clear();
                        mode = twoClickLineMode;
                    }
                },
                new OptionChoosePart.Option("LMB+RMB") {
                    @Override
                    public void select() {
                        mode.clear();
                        mode = leftRightClickLineMode;
                    }
                },
                new OptionChoosePart.Option("Hold line") {
                    @Override
                    public void select() {
                        mode.clear();
                        mode = holdLineMode;
                    }
                }
        );
        if (mode == twoClickLineMode)
            typeChoose.setSelected(0);
        if (mode == leftRightClickLineMode)
            typeChoose.setSelected(1);
        if (mode == holdLineMode)
            typeChoose.setSelected(2);
        NumberChoosePart numberChoosePart = new NumberChoosePart("Size", 1, 20, lineSize) {
            @Override
            protected void setValue(int val) {
                lineSize = val;
            }
        };

        final LineMode startMode = mode;
        final int startLineSize = lineSize;
        TerminatePart terminatePart = new TerminatePart(dialog, () -> {
            lineSize = startLineSize;
            mode = startMode;
        });

        dialog.setTitle("Line params");
        dialog.getContentPane().setLayout(new BoxLayout(dialog.getContentPane(), BoxLayout.Y_AXIS));
        dialog.getContentPane().add(numberChoosePart);
        dialog.getContentPane().add(typeChoose);
        dialog.getContentPane().add(terminatePart);
        dialog.pack();
        Dimension dimension = dialog.getSize();
        dialog.setMinimumSize(dimension);

        return dialog;
    }

    @Override
    public void click(int x, int y, int button) {
        mode.click(x, y, button);
    }

    @Override
    public void press(int x, int y, int button) {
        mode.hold(x, y, button);
    }

    @Override
    public void drag(int x, int y, int button) {
        mode.drag(x, y, button);
    }

    @Override
    public void realise(int x, int y, int button) {
        mode.realise(x, y, button);
    }

}
