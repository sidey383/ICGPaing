package ru.sidey383.icgpaint.toolbar;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.sidey383.icgpaint.iteraction.ComponentInteraction;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class InteractionJButton<T extends ComponentInteraction> extends JButton implements ActionListener {

    private static final Dimension size = new Dimension(40, 40);

    @NotNull
    private final T interaction;
    private BufferedImage image;

    public InteractionJButton(@NotNull T interaction, String description) {
        this(interaction, (BufferedImage) null, description);
    }

    public InteractionJButton(@NotNull T interaction, @Nullable BufferedImage image, String description) {
        super();
        this.interaction = interaction;
        setSize(size);
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setToolTipText(description);
        addActionListener(this);
        if (image != null) {
            setIcon(new ImageIcon(InteractionJButton.getScaledImage(image, getSize(), 10)));
        }
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                if (InteractionJButton.this.image == null)
                    return;
                Dimension size = InteractionJButton.this.getSize();
                InteractionJButton.this.setIcon(new ImageIcon(InteractionJButton.getScaledImage(InteractionJButton.this.image, size, 10)));
            }
        });
    }

    public InteractionJButton(@NotNull T interaction, String icon, String description) {
        this(interaction, InteractionJButton.loadImage(icon), description);
    }

    public InteractionJButton(@NotNull T interaction, Color color, String description) {
        this(interaction, InteractionJButton.getColoredImage(color), description);
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public void setColor(Color color) {
        this.image = InteractionJButton.getColoredImage(color);
        setIcon(new ImageIcon(image));
    }

    @NotNull
    public T getInteraction() {
        return interaction;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        interaction.apply(this);
    }

    protected static BufferedImage getColoredImage(Color color) {
        BufferedImage image = new BufferedImage(size.width - 10, size.height - 10, BufferedImage.TYPE_3BYTE_BGR);
        Graphics2D gr = image.createGraphics();
        gr.setColor(color);
        gr.fillRect(0, 0, size.width, size.height);
        gr.dispose();
        return image;
    }

    public static Image getScaledImage(BufferedImage image, Dimension size, int totalBorder) {
        size.width -= totalBorder;
        size.height -= totalBorder;
        if (size.width > size.height) {
            size.width = -1;
        } else {
            size.height = -1;
        }
        return image.getScaledInstance(size.width, size.height, java.awt.Image.SCALE_SMOOTH);
    }

    public static BufferedImage loadImage(String path) {
        try {
            URL url = PaintToolbar.class.getResource(path);
            if (url == null) {
                return null;
            }
            return ImageIO.read(url);
        } catch (IOException e) {
            System.out.println("Image load error " + path);
            return null;
        }
    }

}
