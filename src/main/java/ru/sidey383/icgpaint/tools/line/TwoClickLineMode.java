package ru.sidey383.icgpaint.tools.line;

public class TwoClickLineMode extends LineMode {

    public int[] lastClick;

    protected TwoClickLineMode(LineDrawer lineDrawer) {
        super(lineDrawer);
    }

    @Override
    void click(int x, int y, int button) {}

    @Override
    void hold(int x, int y, int button) {
        if (lastClick == null) {
            lastClick = new int[]{x, y};
        } else {
            lineDrawer.draw(lastClick[0], lastClick[1], x, y);
            lastClick = null;
        }
    }

    @Override
    void drag(int x, int y, int button) {
    }

    @Override
    void realise(int x, int y, int button) {
    }

    @Override
    void clear() {
        lastClick = null;
    }
}
