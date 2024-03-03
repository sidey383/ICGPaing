package ru.sidey383.icgpaint.tools.line;

public abstract class LineMode {

    protected final LineDrawer lineDrawer;

    protected LineMode(LineDrawer lineDrawer) {
        this.lineDrawer = lineDrawer;
    }

    abstract void click(int x, int y, int button);

    abstract void hold(int x, int y, int button);

    abstract void drag(int x, int y, int button);

    abstract void realise(int x, int y, int button);

    abstract void clear();

}
