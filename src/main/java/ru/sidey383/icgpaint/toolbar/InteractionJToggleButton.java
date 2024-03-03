package ru.sidey383.icgpaint.toolbar;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.sidey383.icgpaint.iteraction.ComponentInteraction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;

public class InteractionJToggleButton<T extends ComponentInteraction> extends JToggleButton implements ItemListener {

    private static final Dimension size = new Dimension(40, 40);

    @NotNull
    private final T interaction;
    private BufferedImage image;

    public InteractionJToggleButton(@NotNull T interaction, String description) {
        this(interaction, (BufferedImage) null, description);
    }

    public InteractionJToggleButton(@NotNull T interaction, @Nullable BufferedImage image, String description) {
        super();
        this.interaction = interaction;
        setSize(size);
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setToolTipText(description);
        addItemListener(this);
        if (image != null) {
            setIcon(new ImageIcon(InteractionJButton.getScaledImage(image, getSize(), 10)));
        }
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                if (InteractionJToggleButton.this.image == null)
                    return;
                Dimension size = InteractionJToggleButton.this.getSize();
                InteractionJToggleButton.this.setIcon(new ImageIcon(InteractionJButton.getScaledImage(InteractionJToggleButton.this.image, size, 10)));
            }
        });
    }

    public InteractionJToggleButton(@NotNull T interaction, String icon, String description) {
        this(interaction, InteractionJButton.loadImage(icon), description);
    }

    public InteractionJToggleButton(@NotNull T interaction, Color color, String description) {
        this(interaction, InteractionJButton.getColoredImage(color), description);
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public void setColor(Color color) {
        this.image = InteractionJButton.getColoredImage(color);
    }

    @NotNull
    public T getInteraction() {
        return interaction;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        interaction.apply(this);
    }
}
