package ru.sidey383.icgpaint.holders;

import org.jetbrains.annotations.NotNull;
import ru.sidey383.icgpaint.holders.ColorHolder;
import ru.sidey383.icgpaint.holders.DrawToolHolder;
import ru.sidey383.icgpaint.tools.DrawTool;
import ru.sidey383.icgpaint.tools.fill.FillDrawTool;
import ru.sidey383.icgpaint.tools.line.LineDrawTool;
import ru.sidey383.icgpaint.tools.stamp.StampDrawTool;

import java.util.Collection;
import java.util.List;

public class BaseDrawTools implements DrawToolHolder {

    @NotNull
    private DrawTool tool;

    @NotNull
    private final LineDrawTool lineDrawTool;

    @NotNull
    private final FillDrawTool fillDrawTool;

    @NotNull
    private final StampDrawTool stampDrawTool;

    public BaseDrawTools(ColorHolder colorHolder) {
        this.lineDrawTool = new LineDrawTool(colorHolder);
        this.fillDrawTool = new FillDrawTool(colorHolder);
        this.stampDrawTool = new StampDrawTool();
        this.tool = this.lineDrawTool;
    }

    @NotNull
    public DrawTool getLineDrawTool() {
        return lineDrawTool;
    }

    @NotNull
    public DrawTool getFillDrawTool() {
        return fillDrawTool;
    }

    public DrawTool getStampDrawTool() {
        return stampDrawTool;
    }

    @NotNull
    public DrawTool getDrawTool() {
        return tool;
    }

    @Override
    public Collection<DrawTool> allTools() {
        return List.of(lineDrawTool, fillDrawTool, stampDrawTool);
    }


    public void setDrawTool(@NotNull DrawTool tool) {
        this.tool = tool;
    }

}
