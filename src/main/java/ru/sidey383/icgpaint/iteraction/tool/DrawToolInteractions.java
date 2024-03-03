package ru.sidey383.icgpaint.iteraction.tool;

import org.jetbrains.annotations.NotNull;
import ru.sidey383.icgpaint.holders.BaseDrawTools;
import ru.sidey383.icgpaint.holders.DrawToolHolder;

public class DrawToolInteractions {
    @NotNull
    private final SingleDrawToolInteraction lineDrawToolInteraction;
    @NotNull
    private final SingleDrawToolInteraction fillDrawToolInteraction;
    @NotNull
    private final SingleDrawToolInteraction stampDrawToolInteraction;
    @NotNull
    private final DrawToolSettingInteraction drawToolSettingInteraction;
    @NotNull
    private final BaseDrawTools baseDrawTools;

    public DrawToolInteractions(@NotNull BaseDrawTools drawToolHolder) {
        this.drawToolSettingInteraction = new DrawToolSettingInteraction(drawToolHolder);
        this.baseDrawTools = drawToolHolder;
        this.lineDrawToolInteraction = new SingleDrawToolInteraction(drawToolHolder, drawToolHolder.getLineDrawTool());
        this.fillDrawToolInteraction = new SingleDrawToolInteraction(drawToolHolder, drawToolHolder.getFillDrawTool());
        this.stampDrawToolInteraction = new SingleDrawToolInteraction(drawToolHolder, drawToolHolder.getStampDrawTool());
    }

    @NotNull
    public SingleDrawToolInteraction getLineDrawToolInteraction() {
        return lineDrawToolInteraction;
    }

    @NotNull
    public SingleDrawToolInteraction getFillDrawToolInteraction() {
        return fillDrawToolInteraction;
    }

    @NotNull
    public SingleDrawToolInteraction getStampDrawToolInteraction() {
        return stampDrawToolInteraction;
    }

    @NotNull
    public DrawToolSettingInteraction getDrawToolSettingInteraction() {
        return drawToolSettingInteraction;
    }

    public DrawToolHolder getDrawToolHolder() {
        return baseDrawTools;
    }
}
