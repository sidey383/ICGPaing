package ru.sidey383.icgpaint.toolbar.tools;

import org.jetbrains.annotations.NotNull;
import ru.sidey383.icgpaint.iteraction.tool.SingleDrawToolInteraction;
import ru.sidey383.icgpaint.toolbar.InteractionJToggleButton;
import ru.sidey383.icgpaint.tools.DrawTool;

import java.awt.event.ItemEvent;

public class ToolButton extends InteractionJToggleButton<SingleDrawToolInteraction> {

    private boolean isChange = false;


    public ToolButton(@NotNull SingleDrawToolInteraction singleDrawToolInteraction, @NotNull String icon, @NotNull String description) {
        super(singleDrawToolInteraction, icon, description);
        addItemListener(this);
    }

    public DrawTool getDrawTool() {
        return  getInteraction().getDrawTool();
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        isChange = true;
        super.itemStateChanged(e);
        isChange = false;
    }

    public boolean isChange() {
        return isChange;
    }

}
