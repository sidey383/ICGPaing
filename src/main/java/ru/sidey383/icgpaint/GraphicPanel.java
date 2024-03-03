package ru.sidey383.icgpaint;

import org.jetbrains.annotations.NotNull;
import ru.sidey383.icgpaint.holders.DrawToolHolder;
import ru.sidey383.icgpaint.holders.ImageHolder;
import ru.sidey383.icgpaint.tools.DrawToolContext;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

public class GraphicPanel extends JPanel implements MouseListener, MouseMotionListener, ImageHolder {

    private static final int imageType = BufferedImage.TYPE_INT_RGB;

    private static final Color background = Color.WHITE;

    @NotNull
    private Dimension size;

    @NotNull
    private BufferedImage bufferImage;

    @NotNull
    private final DrawToolHolder drawToolContext;

    public GraphicPanel(@NotNull DrawToolHolder drawToolContext) {
        setFocusable(false);
        this.drawToolContext = drawToolContext;
        size = new Dimension(1000, 1000);
        bufferImage = createBaseImage(size.width, size.height);
        setMinimumSize(size);
        setMaximumSize(size);
        setPreferredSize(size);
        addMouseListener(this);
        addMouseMotionListener(this);
        setMinimumSize(size);
        setMaximumSize(size);
        setSize(size);
        DrawToolContext context = new DrawToolContext(bufferImage, this::repaint);
        drawToolContext.allTools().forEach(t -> t.setToolContext(context));
    }



    private static BufferedImage createBaseImage(int width, int height) {
        BufferedImage image = new BufferedImage(width, height,  imageType);
        Graphics2D graphics = image.createGraphics();
        graphics.setPaint(background);
        graphics.fillRect(0, 0, width, height);
        graphics.dispose();
        return image;
    }


    @Override
    public void paint(Graphics g) {
        g.drawImage(bufferImage, 0, 0, this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        drawToolContext.getDrawTool().click(e.getX(), e.getY(), e.getButton());
    }

    @Override
    public void mousePressed(MouseEvent e) {
        drawToolContext.getDrawTool().press(e.getX(), e.getY(), e.getButton());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        drawToolContext.getDrawTool().realise(e.getX(), e.getY(), e.getButton());
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseDragged(MouseEvent e) {
        drawToolContext.getDrawTool().drag(e.getX(), e.getY(), e.getButton());
    }

    @Override
    public void mouseMoved(MouseEvent e) {}

    @Override
    public void setImage(@NotNull BufferedImage image) {
        size = new Dimension(image.getWidth(), image.getHeight());
        setMinimumSize(size);
        setMaximumSize(size);
        setSize(size);
        bufferImage = image;
        DrawToolContext context = new DrawToolContext(bufferImage, this::repaint);
        drawToolContext.allTools().forEach(t -> t.setToolContext(context));
        repaint();
    }

    @Override
    public @NotNull BufferedImage getImage() {
        return bufferImage;
    }

    @Override
    public void resizeField(int width, int height) {
        BufferedImage image = new BufferedImage(size.width, size.height, imageType);
        Graphics newGraphic = image.getGraphics();
        newGraphic.drawImage(bufferImage, 0, 0, null);
        newGraphic.dispose();
        bufferImage = image;
        DrawToolContext context = new DrawToolContext(bufferImage, this::repaint);
        drawToolContext.allTools().forEach(t -> t.setToolContext(context));
        repaint();
    }

    @Override
    public void clearImage() {
        Graphics2D gr = bufferImage.createGraphics();
        gr.setColor(background);
        gr.fillRect(0, 0, size.width, size.height);
        repaint();
    }

    @Override
    public int width() {
        return bufferImage.getWidth();
    }

    @Override
    public int height() {
        return bufferImage.getHeight();
    }
}
