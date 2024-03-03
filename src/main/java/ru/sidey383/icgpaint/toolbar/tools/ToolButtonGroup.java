package ru.sidey383.icgpaint.toolbar.tools;

import org.jetbrains.annotations.NotNull;
import ru.sidey383.icgpaint.holders.DrawToolUpdateListener;
import ru.sidey383.icgpaint.iteraction.tool.DrawToolInteractions;
import ru.sidey383.icgpaint.toolbar.InteractionJButton;
import ru.sidey383.icgpaint.tools.DrawTool;

import javax.swing.*;
import java.util.Collection;
import java.util.List;

public class ToolButtonGroup extends ButtonGroup implements DrawToolUpdateListener {

    private final ToolButton lineButton;

    private final ToolButton fillButton;

    private final ToolButton stampButton;

    @NotNull
    private final AbstractButton toolSettingsButton;

    public ToolButtonGroup(@NotNull DrawToolInteractions drawToolInteractions) {
        this.lineButton = new ToolButton(drawToolInteractions.getLineDrawToolInteraction(), "/icon/brush.png", "Line draw instrument");
        this.fillButton = new ToolButton(drawToolInteractions.getFillDrawToolInteraction(), "/icon/bucket.png", "Fill instrument");
        this.stampButton = new ToolButton(drawToolInteractions.getStampDrawToolInteraction(),"/icon/star.png", "Stamp instrument");
        add(lineButton);
        add(fillButton);
        add(stampButton);

        this.toolSettingsButton = new InteractionJButton<>(drawToolInteractions.getDrawToolSettingInteraction(), "/icon/settings.png", "Open settings for current instrument");
        add(this.toolSettingsButton);

        drawToolInteractions.getDrawToolHolder().addListener(this);
        setSelected(lineButton.getModel(), true);
    }

    public Collection<ToolButton> getToolsButtons() {
        return List.of(lineButton, fillButton, stampButton);
    }

    @NotNull
    public AbstractButton getToolSettingsButton() {
        return toolSettingsButton;
    }

    @Override
    public void onDrawToolUpdate(DrawTool tool) {
        getToolsButtons().forEach((b) -> {
            if (tool == b.getDrawTool()) {
                if (!isSelected(b.getModel()) && !b.isChange()) {
                    setSelected(b.getModel(), true);
                }
            }
        });
    }
}
