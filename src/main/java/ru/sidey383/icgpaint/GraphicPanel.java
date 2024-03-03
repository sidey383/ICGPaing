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

    private final JScrollPane scrollPane;

    @NotNull
    private BufferedImage bufferImage;

    @NotNull
    private final DrawToolHolder drawToolContext;

    public GraphicPanel(@NotNull DrawToolHolder drawToolContext) {
        super();
        setFocusable(false);
        this.drawToolContext = drawToolContext;
        setPreferredSize(new Dimension(1000, 1000));
        bufferImage = createBaseImage(1000, 1000);
        addMouseListener(this);
        addMouseMotionListener(this);
        DrawToolContext context = new DrawToolContext(bufferImage, this::repaint);
        drawToolContext.allTools().forEach(t -> t.setToolContext(context));
        scrollPane = new JScrollPane(this);
        setBackground(Color.GRAY);
        scrollPane.setBackground(Color.GRAY);
    }

    public JScrollPane getScrollPane() {
        return scrollPane;
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
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
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
        bufferImage = image;
        DrawToolContext context = new DrawToolContext(bufferImage, this::repaint);
        drawToolContext.allTools().forEach(t -> t.setToolContext(context));
        setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));
        repaint();
    }

    @Override
    public @NotNull BufferedImage getImage() {
        return bufferImage;
    }

    @Override
    public void resizeField(int width, int height) {
        BufferedImage image = createBaseImage(width, height);
        Graphics2D gr = image.createGraphics();
        gr.drawImage(bufferImage, 0, 0, null);
        bufferImage = image;
        DrawToolContext context = new DrawToolContext(bufferImage, this::repaint);
        drawToolContext.allTools().forEach(t -> t.setToolContext(context));
        setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));
        scrollPane.updateUI();
        scrollPane.repaint();
        repaint();
    }

    @Override
    public void clearImage() {
        Graphics2D gr = bufferImage.createGraphics();
        gr.setColor(background);
        gr.fillRect(0, 0, bufferImage.getWidth(), bufferImage.getHeight());
        repaint();
    }

}
