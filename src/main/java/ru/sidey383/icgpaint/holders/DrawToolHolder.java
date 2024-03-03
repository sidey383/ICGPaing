package ru.sidey383.icgpaint.holders;

import org.jetbrains.annotations.NotNull;
import ru.sidey383.icgpaint.tools.DrawTool;

import java.util.Collection;

public interface DrawToolHolder {

    @NotNull
    DrawTool getDrawTool();

    Collection<DrawTool> allTools();

    void setDrawTool(@NotNull DrawTool tool);

}
