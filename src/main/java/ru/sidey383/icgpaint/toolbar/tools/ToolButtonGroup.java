package ru.sidey383.icgpaint.toolbar.tools;

import org.jetbrains.annotations.NotNull;
import ru.sidey383.icgpaint.holders.BaseDrawTools;
import ru.sidey383.icgpaint.holders.DrawToolUpdateListener;
import ru.sidey383.icgpaint.tools.DrawTool;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class ToolButtonGroup extends ButtonGroup implements DrawToolUpdateListener {

    private final ToolButton lineButton;

    private final ToolButton fillButton;

    private final ToolButton stampButton;

    @NotNull
    private final ToolSettingsButton toolSettingsButton;

    public ToolButtonGroup(@NotNull BaseDrawTools tools) {
        this.lineButton = new ToolButton(tools, tools.getLineDrawTool(), Objects.requireNonNull(loadIcon("/icon/brush.png")), "Line draw instrument");
        this.fillButton = new ToolButton(tools, tools.getFillDrawTool(), Objects.requireNonNull(loadIcon("/icon/bucket.png")), "Fill instrument");
        this.stampButton = new ToolButton(tools, tools.getStampDrawTool(), Objects.requireNonNull(loadIcon("/icon/star.png")), "Stamp instrument");
        this.toolSettingsButton = new ToolSettingsButton(tools, Objects.requireNonNull(loadIcon("/icon/settings.png")));
        add(lineButton);
        add(fillButton);
        add(stampButton);
        tools.addListener(this);
        setSelected(lineButton.getModel(), true);
    }

    public Collection<ToolButton> getToolsButtons() {
        return List.of(lineButton, fillButton, stampButton);
    }

    @NotNull
    public ToolSettingsButton getToolSettingsButton() {
        return toolSettingsButton;
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

    @Override
    public void onDrawToolUpdate(DrawTool tool) {
        if (tool == lineButton.getDrawTool() && getSelection().equals(lineButton.getModel()))
            setSelected(lineButton.getModel(), true);
        if (tool == fillButton.getDrawTool() && getSelection().equals(fillButton.getModel()))
            setSelected(fillButton.getModel(), true);
        if (tool == stampButton.getDrawTool() && getSelection().equals(stampButton.getModel()))
            setSelected(stampButton.getModel(), true);
    }
}
