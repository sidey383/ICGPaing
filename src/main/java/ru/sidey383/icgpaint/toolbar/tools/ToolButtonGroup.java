package ru.sidey383.icgpaint.toolbar.tools;

import org.jetbrains.annotations.NotNull;
import ru.sidey383.icgpaint.holders.BaseDrawTools;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class ToolButtonGroup extends ButtonGroup {

    private final ToolButton lineButton;

    private final ToolButton fillButton;

    private final ToolButton stampButton;

    public ToolButtonGroup(@NotNull BaseDrawTools tools) {
        this.lineButton = new ToolButton(tools, tools.getLineDrawTool(), Objects.requireNonNull(loadIcon("/icon/brush.png")), "Line draw instrument");
        this.fillButton = new ToolButton(tools, tools.getFillDrawTool(), Objects.requireNonNull(loadIcon("/icon/bucket.png")), "Fill instrument");
        this.stampButton = new ToolButton(tools, tools.getStampDrawTool(), Objects.requireNonNull(loadIcon("/icon/star.png")), "Stamp instrument");
        add(lineButton);
        add(fillButton);
        add(stampButton);
        setSelected(lineButton.getModel(), true);
    }

    public Collection<ToolButton> getButtons() {
        return List.of(lineButton, fillButton, stampButton);
    }

    private BufferedImage loadIcon(String path) {
        try {
            URL url = getClass().getResource(path);
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
