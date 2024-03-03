package ru.sidey383.icgpaint.tools.line;

import java.awt.event.MouseEvent;

public class LeftRightClickLineMode extends LineMode {

    public int[] leftClick;

    protected LeftRightClickLineMode(LineDrawer lineDrawer) {
        super(lineDrawer);
    }

    @Override
    void click(int x, int y, int button) {
        switch (button) {
            case MouseEvent.BUTTON1 ->
                leftClick = new int[] {x, y};
            case MouseEvent.BUTTON3 -> {
                if (leftClick != null)
                    lineDrawer.draw(leftClick[0], leftClick[1], x, y);
            }
        }
    }

    @Override
    void hold(int x, int y, int button) {
        switch (button) {
            case MouseEvent.BUTTON1 ->
                    leftClick = new int[] {x, y};
            case MouseEvent.BUTTON3 -> {
                if (leftClick != null)
                    lineDrawer.draw(leftClick[0], leftClick[1], x, y);
            }
        }
    }

    @Override
    void drag(int x, int y, int button) {}

    @Override
    void realise(int x, int y, int button) {}

    @Override
    void clear() {
        leftClick = null;
    }
}
