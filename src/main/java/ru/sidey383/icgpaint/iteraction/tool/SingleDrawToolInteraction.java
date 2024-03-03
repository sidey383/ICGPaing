package ru.sidey383.icgpaint.iteraction.tool;

import org.jetbrains.annotations.NotNull;
import ru.sidey383.icgpaint.holders.DrawToolHolder;
import ru.sidey383.icgpaint.iteraction.ComponentInteraction;
import ru.sidey383.icgpaint.tools.DrawTool;

import java.awt.*;

public class SingleDrawToolInteraction implements ComponentInteraction {

    @NotNull
    private final DrawToolHolder drawToolHolder;

    @NotNull
    private final DrawTool drawTool;

    public SingleDrawToolInteraction(@NotNull DrawToolHolder drawToolHolder, @NotNull DrawTool tool) {
        this.drawTool = tool;
        this.drawToolHolder = drawToolHolder;
    }

    @Override
    public void apply(Component component) {
        drawToolHolder.setDrawTool(drawTool);
    }

    @NotNull
    public  DrawTool getDrawTool() {
        return drawTool;
    }
}
