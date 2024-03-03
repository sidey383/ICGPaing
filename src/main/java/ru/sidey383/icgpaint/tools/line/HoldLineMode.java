package ru.sidey383.icgpaint.tools.line;

public class HoldLineMode extends LineMode {

    int[] lastClick;

    protected HoldLineMode(LineDrawer lineDrawer) {
        super(lineDrawer);
    }

    @Override
    void click(int x, int y, int button) {}

    @Override
    void hold(int x, int y, int button) {
        lastClick = new int[] {x, y};
    }

    @Override
    void drag(int x, int y, int button) {
        if (lastClick != null) {
            lineDrawer.draw(lastClick[0], lastClick[1], x, y);
        }
        lastClick = new int[]{x, y};
    }

    @Override
    void realise(int x, int y, int button) {
        if (lastClick != null) {
            lineDrawer.draw(lastClick[0], lastClick[1], x, y);
        }
        lastClick = null;
    }

    @Override
    void clear() {
        lastClick = null;
    }
}
