package ru.sidey383.icgpaint.tools.stamp;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.sidey383.icgpaint.tools.DrawTool;
import ru.sidey383.icgpaint.tools.dialog.NumberChoosePart;
import ru.sidey383.icgpaint.tools.dialog.OptionChoosePart;
import ru.sidey383.icgpaint.tools.dialog.TerminatePart;
import ru.sidey383.icgpaint.tools.line.LineMode;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class StampDrawTool extends DrawTool {

    private int size = 80;

    private int n = 7;

    private double rotation = 0;

    private final RegularPolygonStampMode regularPolygonStampMode = new RegularPolygonStampMode();

    private final StarPolygonStampMode starPolygonStampMode = new StarPolygonStampMode();

    private StampMode mode = regularPolygonStampMode;


    @Override
    public @Nullable JDialog editDialog() {
        JDialog dialog = new JDialog();
        NumberChoosePart sizeChoose = new NumberChoosePart("Size", 5, 500, size) {
            @Override
            protected void setValue(int val) {
                size = val;
            }
        };
        NumberChoosePart nChoose = new NumberChoosePart("Vertex count", 3, 16, n) {
            @Override
            protected void setValue(int val) {
                n = val;
            }
        };
        NumberChoosePart angleChoose = new NumberChoosePart("Angle", 0, 360, (int) rotation) {
            @Override
            protected void setValue(int val) {
                rotation = val;
            }
        };
        OptionChoosePart typeChoose = createOptionChoosePart();

        final StampMode startMode = mode;
        final int startSize = size;
        final double startRotation = rotation;
        final int startN = n;
        TerminatePart terminatePart = new TerminatePart(dialog, () -> {
            mode = startMode;
            size = startSize;
            rotation = startRotation;
            n = startN;
        });

        dialog.setTitle("Stamp params");
        dialog.getContentPane().setLayout(new BoxLayout(dialog.getContentPane(), BoxLayout.Y_AXIS));
        dialog.getContentPane().add(sizeChoose);
        dialog.getContentPane().add(nChoose);
        dialog.getContentPane().add(angleChoose);
        dialog.getContentPane().add(typeChoose);
        dialog.getContentPane().add(terminatePart);
        dialog.pack();
        Dimension dimension = dialog.getSize();
        dialog.setMinimumSize(dimension);
        return dialog;
    }

    @NotNull
    private OptionChoosePart createOptionChoosePart() {
        OptionChoosePart.Option base = new OptionChoosePart.Option("Regular polygon") {
            @Override
            public void select() {
                mode = regularPolygonStampMode;
            }
        };
        OptionChoosePart.Option star = new OptionChoosePart.Option("Star") {
            @Override
            public void select() {
                mode = starPolygonStampMode;
            }
        };
        OptionChoosePart typeChoose = new OptionChoosePart(base, star);
        if (mode == regularPolygonStampMode)
            typeChoose.setSelected(0);
        if (mode == starPolygonStampMode)
            typeChoose.setSelected(1);
        return typeChoose;
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

    protected void draw(int x, int y) {
        BufferedImage image = image();
        if (image == null)
            return;
        int[][] dots = mode.getPoints(x, y, rotation, n, size);
        for (int i = 0; i < dots.length; i++) {
            runAlgorithm(image, Color.BLACK.getRGB(), dots[i][0], dots[i][1], dots[(i+1)%dots.length][0], dots[(i+1)%dots.length][1]);
        }
        updateImage();
    }

    @Override
    public void click(int x, int y, int button) {
        draw(x, y);
    }
}
